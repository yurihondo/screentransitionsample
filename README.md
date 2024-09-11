# Screen Transition Sample

This repository demonstrates various screen transition techniques using Jetpack Compose and Navigation.

## Features

1. [Managing Tab History](#managing-tab-history)
2. [Toggling Tab Visibility](#toggling-tab-visibility)
3. [DeepLinks Handling](#deeplinks-handling)
4. [Safe Passing of NavArgs](#safe-passing-of-navargs)

### 1. Managing Tab History

In this sample, each tab has its own dedicated navigation graph. When a tab is selected, the corresponding graph is switched, and the backstack for that tab is restored. Additionally, tab selection history is managed so that you can navigate back to the previously selected tab by pressing the back button.
The following code is related.
`topLevelDestinationBackQueue` manages the tab history.

**AppState.kt**

```kotlin
fun onSelectTopLevelDestination(destination: TopLevelDestination) {
    navigateToTopLevelDestination(destination)
    coreData.topLevelDestinationBackQueue.add(destination)
}

private fun navigateToTopLevelDestination(destination: TopLevelDestination) {
    with(destination.graph()) {
        val option = navOptions {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        navHostController.navigate(option)
    }
}

fun onBack() {
    if (isInStartRoute()) {
        // Remove current BackStack from queue and check next one.
        coreData. topLevelDestinationBackQueue.remove()
        coreData.topLevelDestinationBackQueue.element()?.let { dest ->
            navigateToTopLevelDestination(dest)
            coreData.currentTopLevelDestination = dest
        } ?: navHostController.context.findActivity().finish()
    } else {
        if (navHostController.popBackStack().not()) {
            navHostController.context.findActivity().finish()
        }
    }
}
```

This approach ensures smooth transitions between tabs, with each tab's state being preserved independently.

### 2. Toggling Tab Visibility

The visibility of tabs is dynamically controlled based on the navigation destination. Each destination defines whether the tab bar should be displayed, and the tab bar is toggled accordingly as the user navigates between different screens.

**AppState.kt**

```kotlin
companion object {
    private val TOP_LEVEL_NAVIGATION_BEHAVIOR_MAP = mapOf(
        applePieMr1NavigationRoute to HIDE,
        bananaBreadMr1NavigationRoute to SAME_AS_PARENT,
    )
}

init {
    navHostController.addOnDestinationChangedListener { navController, dest, _ ->
        val behaviorType = dest.route?.let { route ->
            TOP_LEVEL_NAVIGATION_BEHAVIOR_MAP[route]
        }
        when (behaviorType) {
            HIDE -> hideNavigation()
            SAME_AS_PARENT -> {
                val isBackEventDetected = navController.currentBackStack.value.any { entry ->
                    entry == navController.currentBackStackEntry
                }
                if (isBackEventDetected) {
                    navController.currentBackStackEntry?.let { entry ->
                        when (navController.currentBackStack.value
                            .findActualParentTopLevelNavigationBehavior(entry)
                        ) {
                            HIDE -> hideNavigation()
                            else -> showNavigation()
                        }
                    }
                } else {
                    Unit // NOP
                }
            }
            else -> showNavigation()
        }
    }
}

/**
 * Find the actual top level navigation (bottom navigation / navigation rail) behavior (hide or show)
 * for [SAME_AS_PARENT] type, by iterating the back stack entries (ancestor screens).
 */
private fun List<NavBackStackEntry>.findActualParentTopLevelNavigationBehavior(
    target: NavBackStackEntry
): TopLevelDestinationBehavior? {
    val targetIndex = indexOf(target)
    if (targetIndex == -1) return null // not found
    // Take the entries up to 'targetIndex' and reverse the order.
    // Reverse the order to check entries from the target index backwards, to find the behavior of parent entries.
    for (entry in take(targetIndex).asReversed()) {
        // NavBackStackEntry includes those intended for Graph as well as those for Screen.
        // This can be distinguished by looking at the NavDestination held by NavBackStackEntry.
        // Specifically, NavDestination is a base class that has concrete classes such as Graph (ComposeNavGraph) and Screen (Destination), so it should be checked.
        // To obtain the Behavior for each screen, the NavDestination is checked to ensure it is a Destination.
        if (entry.destination is Destination) {
            val behavior = TOP_LEVEL_NAVIGATION_BEHAVIOR_MAP[entry.destination.route]
            if (behavior != SAME_AS_PARENT) {
                return behavior
            }
        }
    }
    return null
}
```

### 3. DeepLinks Handling

This sample project supports both basic DeepLink handling using the standard Navigation Compose mechanism and special cases through a custom DeepLinksActivity. By using DeepLinksActivity, it is possible to handle specific cases, such as modifying the way screens are stacked based on the parameters in the DeepLink, which the standard behavior cannot cover.
The following code is related.

**DeepLinksActivity.kt**

```kotlin
class DeepLinksActivity : ComponentActivity() {

    private fun handleIntent(intent: Intent) {
        val uri = intent.data ?: return
        when (uri.scheme) {
            CUSTOM_URI_SCHEME -> {
                when (uri.authority) {
                    AUTHORITY_SHOW_BANANA_BREAD_MR1 -> {
                        val exitApp = uri.getBooleanQueryParameter(EXIT_APP, false)
                        startActivity(
                            MainActivity.createIntentToShowBananaBreadMr1(
                                activityContext = this@DeepLinksActivity,
                                clearStack = exitApp,
                            ).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        )
                    }
                }
            }
        }
    }
}
```

**MainActivity.kt**

```kotlin
class MainActivity : ComponentActivity() {
    companion object {
        fun createIntentToShowBananaBreadMr1(activityContext: Context, clearStack: Boolean): Intent {
            return Intent(activityContext, MainActivity::class.java).apply {
                action = DeepLinksNavigator.ACTION_SHOW_BANANA_BREAD_MR1
                putExtra(DeepLinksNavigator.KEY_CLEAR_STACK, clearStack)
            }
        }
    }
}
```

**DeepLinksNavigator.kt**

```kotlin
internal interface DeepLinksNavigator {

    companion object {
        // actions
        const val ACTION_SHOW_BANANA_BREAD_MR1 = "com.yurihondo.screentransitionsample.action.SHOW_BANANA_BREAD_MR1"

        // keys
        const val KEY_CLEAR_STACK = "clear_stack"
    }

    fun handleDeepLinksIfNeeded(intent: Intent): Boolean {
        var consumed = true
        when (intent.action) {
            ACTION_SHOW_BANANA_BREAD_MR1 -> {
                val clearStack = intent.getBooleanExtra(KEY_CLEAR_STACK, false)
                navigateToBananaBreadMr1GraphFromExternal(clearStack)
            }
            else -> {
                consumed = false
            }
        }
        return consumed
    }

    fun navigateToBananaBreadMr1GraphFromExternal(clearStack: Boolean)
}
```

**MainNavigator.kt**

```kotlin
override fun navigateToBananaBreadMr1GraphFromExternal(clearStack: Boolean) {
    navController.navigateToBananaBreadMr1Graph(
        navOptions {
            if (clearStack) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    )
}
```

### 4. Safe Passing of NavArgs

To ensure safe passing of NavArgs, this repository uses type-safe methods for passing arguments during navigation. This reduces errors during screen transitions and argument passing, resulting in more reliable transitions. With Navigation 2.7,
the following method is used to define functions for screen navigation with type checks.

**ApplePieNavigation.kt**

```kotlin
fun NavController.navigateToApplePieMr1Route(
    from: String,
    navOptions: NavOptions? = null
) {
    this.navigate("$applePieMr1NavigationRoute/$from", navOptions)
}

composable(
    route = applePieMr1NavigationRoute,
    arguments = listOf(
        navArgument(applePieMr1NavigationParamFrom) { defaultValue = "unknown" }
    ),
    deepLinks = ...,
) { entry ->
    ApplePieMr1Route(
        from = entry.arguments?.getString(applePieMr1NavigationParamFrom)!!,
        onClickMoveBananaBreadMr1 = navigateToBananaBreadMr1Graph
    )
}
```
