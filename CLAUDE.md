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

This is an Android sample app demonstrating Navigation Compose 2.8+ patterns with type-safe routes and multi-module architecture.

### Module Structure

- **app**: Main application module containing `MainActivity`, `AppState`, navigation host, and theme
- **core/ui**: Shared navigation interfaces (`GraphSpec`, `Destination`)
- **core/common**: Common utilities and extensions
- **feature/**: Feature modules (applepie, bananabread, cupcake, donut, eclair) - each represents a tab

### Navigation Architecture

Each feature module defines:
1. **NavRoute.kt**: `@Serializable` route definitions for type-safe navigation (e.g., `ApplePieDestination`, `ApplePieMr1Destination`)
2. **GraphSpec.kt**: Implements `GraphSpec` interface to define the navigation graph entry point and start route
3. **Navigation.kt**: Extension function on `NavGraphBuilder` to register composable destinations

The `AppState` class in `app/src/main/java/.../core/ui/AppState.kt` manages:
- Tab history via `LifoUniqueQueue` for back navigation between tabs
- Bottom navigation visibility based on `TopLevelDestinationBehavior` (HIDE, SAME_AS_PARENT, or default show)
- Navigation to top-level destinations with state preservation

### Key Patterns

**Type-safe routes**: Routes use `@Serializable` data classes/objects. Navigate with the route object, retrieve args via `backStackEntry.toRoute<RouteType>()`.

**Sending results back**: Use `savedStateHandle` on the previous back stack entry to pass results between screens.

**Deep links**: Custom handling via `DeepLinksActivity` → `MainActivity` for complex scenarios beyond standard Navigation deep link support.