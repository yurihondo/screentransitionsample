# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build the project
./gradlew build

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Clean and rebuild
./gradlew clean build

# Build specific module
./gradlew :app:build
./gradlew :feature:applepie:build
```

## Architecture

This is an Android sample app demonstrating Navigation 3 patterns with type-safe routes and multi-module architecture.

### Module Structure

- **app**: Main application module containing `MainActivity`, `AppState`, `NavigationState`, navigation display, and theme
- **core/ui**: Shared UI components
- **core/common**: Common utilities and extensions
- **feature/**: Feature modules (applepie, bananabread, cupcake, donut, eclair) - each represents a tab

### Navigation Architecture (Navigation 3)

Navigation 3 uses a flat structure with `NavKey` and `EntryProvider`:

**Route definitions** (`feature/*/navigation/NavRoute.kt`):
```kotlin
@Serializable
data object ApplePie : NavKey

@Serializable
data class ApplePieMr1(val from: String) : NavKey
```

**Entry Provider** (`app/.../navigation/MainEntryProvider.kt`):
```kotlin
entryProvider<NavKey> {
    entry<ApplePie> { ApplePieRoute(...) }
    entry<ApplePieMr1> { key -> ApplePieMr1Route(from = key.from, ...) }
}
```

**Navigation Display** (`app/.../navigation/MainNavDisplay.kt`):
```kotlin
NavDisplay(
    backStack = appState.navigationState.currentBackStack(),
    entryProvider = entryProvider,
    onBack = onBack,
)
```

### State Management

- **NavigationState**: Manages multiple back stacks for each top-level destination
- **AppState**: Coordinates navigation, tab history via `LifoUniqueQueue`, and bottom navigation visibility
- **ResultStore**: Passes results between screens using `CompositionLocal` (nav3-recipes pattern)

### Key Patterns

**Type-safe routes**: Routes implement `NavKey` interface with `@Serializable` annotation.

**Result passing**: Use `ResultStore` with `LaunchedEffect` to pass results between screens:
```kotlin
// Sender
resultStore.setResult(result, KEY)
appState.onBack {}

// Receiver
val result by resultStore.getResultState<String>(KEY)
LaunchedEffect(result) {
    if (result != null) {
        consumedResult = result
        resultStore.removeResult(KEY)
    }
}
```

**Deep links**: Custom handling via `DeepLinksActivity` → `MainActivity` for complex scenarios.
