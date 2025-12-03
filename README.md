# Screen Transition Sample for Navigation 3

This repository demonstrates various screen transition techniques using Jetpack Compose and Navigation 3.

## Branches

| Branch | Navigation Library | Description |
|--------|-------------------|-------------|
| [`main`](https://github.com/yurihondo/screentransitionsample) | Navigation 3 | Current implementation with Nav3 |
| [`feature/navigation_v2.8.x`](https://github.com/yurihondo/screentransitionsample/tree/feature/navigation_v2.8.x) | Navigation Compose 2.8 | Type-safe routes with `@Serializable` |
| [`feature/navigation_v2.7.x`](https://github.com/yurihondo/screentransitionsample/tree/feature/navigation_v2.7.x) | Navigation Compose 2.7 | Original implementation |
| [`feature/compose_destinations`](https://github.com/yurihondo/screentransitionsample/tree/feature/compose_destinations) | Compose Destinations | Using compose-destinations library |

## Features

1. [Managing Tab History](#1-managing-tab-history)
2. [Toggling Tab Visibility](#2-toggling-tab-visibility)
3. [DeepLinks Handling](#3-deeplinks-handling)
4. [Safe Passing of NavArgs](#4-safe-passing-of-navargs)
5. [Send results back](#5-send-results-back)

### 1. Managing Tab History

Each tab has its own back stack managed by `NavigationState`, following the [nav3-recipes multiplestacks](https://github.com/android/nav3-recipes/tree/main/app/src/main/java/com/example/nav3recipes/multiplestacks) pattern. Back stacks survive configuration changes and process death via `rememberNavBackStack`. Tab selection history is managed via `LifoUniqueQueue` so that pressing back navigates to the previously selected tab.

**NavigationState.kt**

```kotlin
@Composable
fun rememberNavigationState(
    startRoute: NavKey,
    topLevelRoutes: Set<NavKey>,
): NavigationState {
    val topLevelRoute = rememberSerializable(
        startRoute, topLevelRoutes,
        serializer = MutableStateSerializer(NavKeySerializer())
    ) { mutableStateOf(startRoute) }

    val backStacks = topLevelRoutes.associateWith { key -> rememberNavBackStack(key) }

    return remember(startRoute, topLevelRoutes) {
        NavigationState(topLevelRoute, backStacks)
    }
}

@Stable
class NavigationState(
    topLevelRoute: MutableState<NavKey>,
    val backStacks: Map<NavKey, NavBackStack<NavKey>>,
) {
    var topLevelRoute: NavKey by topLevelRoute
    fun currentBackStack(): List<NavKey> = backStacks[topLevelRoute] ?: emptyList()
}

@Composable
fun NavigationState.toEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>
): SnapshotStateList<NavEntry<NavKey>> {
    val decoratedEntries = backStacks.mapValues { (_, stack) ->
        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = entryProvider
        )
    }
    return listOf(topLevelRoute)
        .flatMap { decoratedEntries[it] ?: emptyList() }
        .toMutableStateList()
}
```

**MainNavDisplay.kt**

```kotlin
NavDisplay(
    entries = appState.navigationState.toEntries(entryProvider),
    onBack = onBack,
)
```

**AppState.kt**

```kotlin
fun onSelectTopLevelDestination(destination: TopLevelDestination) {
    navigationState.topLevelRoute = destination.startRoute
    coreData.topLevelDestinationBackQueue.add(destination)
    coreData.currentTopLevelDestination = destination
}

fun onBack(finishActivity: () -> Unit) {
    if (navigationState.isAtStartRoute()) {
        coreData.topLevelDestinationBackQueue.remove()
        coreData.topLevelDestinationBackQueue.element()?.let { dest ->
            navigationState.topLevelRoute = dest.startRoute
            coreData.currentTopLevelDestination = dest
        } ?: finishActivity()
    } else {
        navigationState.popBackStack()
    }
}
```

### 2. Toggling Tab Visibility

The visibility of the bottom navigation bar is dynamically controlled based on the current destination. Each destination can define its visibility behavior.

**AppState.kt**

```kotlin
companion object {
    private val TOP_LEVEL_NAVIGATION_BEHAVIOR_MAP = mapOf(
        ApplePieMr1::class to HIDE,
        BananaBreadMr1::class to SAME_AS_PARENT,
    )
}

val shouldShowNavigation: Boolean by derivedStateOf {
    val currentRoute = navigationState.backStacks[navigationState.topLevelRoute]?.lastOrNull()
    currentRoute?.let { route ->
        when (TOP_LEVEL_NAVIGATION_BEHAVIOR_MAP[route::class]) {
            HIDE -> false
            SAME_AS_PARENT -> shouldShowNavigationState
            else -> true
        }
    } ?: true
}
```

### 3. DeepLinks Handling

This sample supports DeepLink handling through a custom `DeepLinksActivity`. Navigation 3 does not have built-in deep link support, so custom handling is used.

**DeepLinksActivity.kt**

```kotlin
class DeepLinksActivity : ComponentActivity() {
    private fun handleIntent(intent: Intent) {
        val uri = intent.data ?: return
        when (uri.scheme) {
            CUSTOM_URI_SCHEME -> {
                when (uri.authority) {
                    AUTHORITY_SHOW_BANANA_BREAD_MR1 -> {
                        startActivity(
                            MainActivity.createIntentToShowBananaBreadMr1(
                                activityContext = this,
                                clearStack = uri.getBooleanQueryParameter(EXIT_APP, false),
                            )
                        )
                    }
                }
            }
        }
    }
}
```

### 4. Safe Passing of NavArgs

In Navigation 3, route arguments are passed using `@Serializable` classes that implement `NavKey`. When navigating, pass the route object directly, and receive it in the entry lambda.

**NavRoute.kt**

```kotlin
@Serializable
data class ApplePieMr1(
    @SerialName("from")
    val from: String = "unknown",
) : NavKey
```

**MainEntryProvider.kt**

```kotlin
entry<ApplePieMr1> { key ->
    ApplePieMr1Route(
        from = key.from,  // Type-safe access
        onClickMoveBananaBreadMr1 = { ... }
    )
}
```

### 5. Send results back

Navigation 3 recommends using a state-based approach for passing results between screens. This sample uses `ResultStore` with `CompositionLocal`, following the [nav3-recipes](https://github.com/android/nav3-recipes) pattern.

**ResultStore.kt**

```kotlin
val LocalResultStore = staticCompositionLocalOf<ResultStore> { error("No ResultStore") }

class ResultStore {
    fun <T> getResultState(key: String): State<T?>
    fun setResult(result: Any?, key: String)
    fun removeResult(key: String)
}
```

**MainEntryProvider.kt**

```kotlin
entry<ApplePieMr1> { key ->
    val editResult by resultStore.getResultState<String>(EDIT_RESULT_KEY)
    var consumedResult by remember { mutableStateOf<String?>(null) }

    // Consume result via LaunchedEffect (not during composition)
    LaunchedEffect(editResult) {
        if (editResult != null) {
            consumedResult = editResult
            resultStore.removeResult(EDIT_RESULT_KEY)
        }
    }

    val currentFrom = consumedResult ?: key.from
    ApplePieMr1Route(from = currentFrom, ...)
}

entry<Edit> { key ->
    EditRoute(
        from = key.from,
        onDone = { result ->
            resultStore.setResult(result, EDIT_RESULT_KEY)
            appState.onBack {}
        }
    )
}
```

**Key points:**
- Use `LaunchedEffect` to consume results asynchronously (not during composition)
- Use `remember` to hold the consumed result locally
- Clear the result from the store after capturing it

## References

- [Navigation 3 Recipes](https://github.com/android/nav3-recipes)
- [Navigation 3 Migration Guide](https://github.com/android/nav3-recipes/blob/main/docs/migration-guide.md)
