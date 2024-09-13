# Screen Transition Sample　for　Compose Destinations

This repository demonstrates various screen transition techniques using Jetpack Compose and Navigation using compose destinations.
Plz clone and run this repository if you try this sample app.

## Introduction to Compose Destinations

[Compose Destinations](https://github.com/raamcosta/compose-destinations) is a powerful library that leverages Kotlin Symbol Processing (KSP) to generate type-safe navigation code for Jetpack Compose. By automatically generating boilerplate code, it reduces the amount of manual setup required for passing arguments and managing navigation in a type-safe manner. This makes handling navigation in Compose simpler and less error-prone, while maintaining flexibility and code readability.

## Main Differences from Vanilla Navigation

1. **Automatic Graph Generation**  
   In Compose Destinations, navigation graphs are automatically generated based on the `@Destination` annotations added to your screens. This eliminates the need to manually define navigation graphs, streamlining the setup process.

2. **KSP-Generated Destination Classes**  
   Instead of manually handling screen transitions, Compose Destinations generates `Destination` classes for each screen using Kotlin Symbol Processing (KSP). These generated classes handle navigation and argument passing, reducing the amount of boilerplate code. Screen-specific control and navigation logic is also handled through these `Destination` classes, making the navigation flow more type-safe and maintainable.


## Features

1. [Managing Tab History](#managing-tab-history)
2. [Toggling Tab Visibility](#toggling-tab-visibility)
3. [DeepLinks Handling](#deeplinks-handling)
4. [Safe Passing of NavArgs](#safe-passing-of-navargs)
5. [Send results back](#send-results-back)

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
    destinationsNavigator.navigate(destination.graph()) {
        popUpTo(navHostController.navGraph.startDestination) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun onBack() {
    if (isInStartRoute()) {
        // Remove current BackStack from queue and check next one.
        coreData.topLevelDestinationBackQueue.remove()
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

private fun isInStartRoute(): Boolean {
    val startRouteOnCurrentDest = currentTopLevelDestination.graph().startRoute.route
    return navHostController.currentBackStackEntry?.destination?.route == startRouteOnCurrentDest
}
```

This approach ensures smooth transitions between tabs, with each tab's state being preserved independently.

### 2. Toggling Tab Visibility

The visibility of tabs is dynamically controlled based on the navigation destination. Each destination defines whether the tab bar should be displayed, and the tab bar is toggled accordingly as the user navigates between different screens.

**AppState.kt**

```kotlin
companion object {
    private val TOP_LEVEL_NAVIGATION_BEHAVIOR_MAP = mapOf(
        ApplePieMr1RouteDestination.route to HIDE,
        BananaBreadMr1RouteDestination.route to SAME_AS_PARENT,
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

note:
The reason why it is possible to detect if a screen transition is due to a back event with the following implementation:

```kotlin
navHostController.addOnDestinationChangedListener { navController, dest, _ ->
    val isBackEventDetected = navController.currentBackStack.value.any { entry ->
        entry == navController.currentBackStackEntry
    }
}
```

is because, if we exclude the NavController implementation, the OnDestinationChangedListener callback is executed first, followed by the update of the currentBackStack. Therefore, at the time the callback is received, the currentBackStack has not yet been updated, meaning that the state of the previous screen can be obtained.

**NavController.kt**

```kotlin
private fun dispatchOnDestinationChanged(): Boolean {
    ...
        for (backStackEntry in dispatchList) {
            // Now call all registered OnDestinationChangedListener instances
            for (listener in onDestinationChangedListeners) {
                listener.onDestinationChanged(
                    this,
                    backStackEntry.destination,
                    backStackEntry.arguments
                )
            }
            _currentBackStackEntryFlow.tryEmit(backStackEntry)
        }
        _currentBackStack.tryEmit(backQueue.toMutableList())
        _visibleEntries.tryEmit(populateVisibleEntries())
    }
    return lastBackStackEntry != null
}
```

As a result, during a normal transition, the destination passed in the callback does not exist when scanning the currentBackStack within the callback. However, if a screen transition occurs due to a back event, the destination passed in the callback will exist in the currentBackStack.

Thus, by scanning the currentBackStack within the OnDestinationChangedListener callback and checking for the presence of the destination, we can determine with certainty that the screen transition was triggered by a back event.

However, this implementation is not highly recommended.

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
override fun navigateToBananaBreadMr1FromExternal(clearStack: Boolean) {
    destinationsNavigator.navigate(BananaBreadMr1RouteDestination) {
        if (clearStack) {
            popUpTo(navController.navGraph.startDestination) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}
```

### 4. Safe Passing of NavArgs

In Compose Destinations, you can define arguments for a screen's `@Destination`-annotated Composable function by either specifying the parameters in the function itself or by setting the argument types in the `@Destination` annotation. Compose Destinations will then automatically handle the passing of arguments with the correct types.

The arguments are stored in `SavedStateHandle`, allowing them to be easily accessed in ViewModels or other components. This makes it convenient to manage state and handle argument passing without needing to write additional boilerplate code. Very handy!


**ApplePieMr1Route.kt**

```kotlin
data class ApplePieMr1Args(
    val from: String = "unknown",
)

@Destination<ApplePieMr1Graph>(
    start = true,
    navArgs = ApplePieMr1Args::class,
    deepLinks = [
        DeepLink(
            uriPattern = "https://com.yurihondo.screentransitionsample/applepie_mr1?from={from}",
        )
    ],
)
@Composable
internal fun ApplePieMr1Route(
    applePieNavigator: ApplePieNavigator,
    args: ApplePieMr1Args,
) {
    ApplePieMr1Screen(
        from = args.from,
        onClickMoveBananaBreadMr1 = applePieNavigator::navigateToBananaBreadMr1,
    )
}
```

### 5. Send results back

Compose Destinations provides a way to send results back to the previous screen when closing a screen, as an alternative to methods like `ActivityForResult`.

To send results back from a screen, Compose Destinations uses the `resultBackNavigator`. The result is sent from the closing screen, and the receiving screen uses the `ResultRecipient` to type-safely receive the result. This ensures a clean and type-safe way to pass data between screens, eliminating the need for manual result handling.

**EditRoute.kt**

```kotlin
@Destination<ApplePieMr1Graph>(
    style = DestinationStyle.Dialog::class
)
@Composable
internal fun EditRoute(
    from: String,
    resultBackNavigator: ResultBackNavigator<String>,
) {
    EditScreen(
        initialValue = from,
        onDone = {
            resultBackNavigator.navigateBack(it)
        },
    )
}
```

**ApplePieMr1Route.kt**

```kotlin
@Composable
internal fun ApplePieMr1Route(
    applePieNavigator: ApplePieNavigator,
    destinationsNavigator: DestinationsNavigator,
    args: ApplePieMr1Args,
    resultRecipient: ResultRecipient<EditRouteDestination, String>
) {
    var from by rememberSaveable { mutableStateOf(args.from) }

    resultRecipient.onResult { editedFrom -> from = editedFrom }

    ApplePieMr1Screen(
        from = from,
        onClickMoveBananaBreadMr1 = applePieNavigator::navigateToBananaBreadMr1,
        onNavigateEdit = { destinationsNavigator.navigate(EditRouteDestination(from)) }
    )
}
```
