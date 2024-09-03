package com.yurihondo.screentransitionsample.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator.Destination
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.yurihondo.applepie.navigation.applePieMr1NavigationRoute
import com.yurihondo.bananabread.navigation.bananaBreadMr1NavigationRoute
import com.yurihondo.screentransitionsample.core.common.extension.findActivity
import com.yurihondo.screentransitionsample.navigation.LifoUniqueQueue
import com.yurihondo.screentransitionsample.navigation.TopLevelDestination
import com.yurihondo.screentransitionsample.navigation.TopLevelDestinationBehavior
import com.yurihondo.screentransitionsample.navigation.TopLevelDestinationBehavior.HIDE
import com.yurihondo.screentransitionsample.navigation.TopLevelDestinationBehavior.SAME_AS_PARENT

@Stable
internal class AppState(
    val navHostController: NavHostController,
) {

    companion object {
        private val TOP_LEVEL_NAVIGATION_BEHAVIOR_MAP = mapOf(
            applePieMr1NavigationRoute to HIDE,
            bananaBreadMr1NavigationRoute to SAME_AS_PARENT,
        )
    }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    var currentTopLevelDestination by mutableStateOf(TopLevelDestination.APPLE_PIE)
        private set

    var shouldShowNavigation by mutableStateOf(true)
        private set

    private val topLevelDestinationBackQueue: LifoUniqueQueue<TopLevelDestination> =
        LifoUniqueQueue()

    init {
        topLevelDestinationBackQueue.add(currentTopLevelDestination)

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

    fun onSelectTopLevelDestination(destination: TopLevelDestination) {
        navigateToTopLevelDestination(destination)
        topLevelDestinationBackQueue.add(destination)
        currentTopLevelDestination = destination
    }

    fun onBack() {
        if (isInStartRoute()) {
            // Remove current BackStack from queue and check next one.
            topLevelDestinationBackQueue.remove()
            topLevelDestinationBackQueue.element()?.let { dest ->
                navigateToTopLevelDestination(dest)
                currentTopLevelDestination = dest
            } ?: navHostController.context.findActivity().finish()
        } else {
            navHostController.popBackStack()
        }
    }

    private fun navigateToTopLevelDestination(destination: TopLevelDestination) {
        with(destination.graph) {
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

    private fun isInStartRoute(): Boolean {
        val startRouteOnCurrentDest = currentTopLevelDestination.graph.startRoute
        return navHostController.currentBackStackEntry?.destination?.route == startRouteOnCurrentDest
    }

    private fun hideNavigation() {
        shouldShowNavigation = false
    }

    private fun showNavigation() {
        shouldShowNavigation = true
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
}

@Composable
internal fun rememberAppState(
    navHostController: NavHostController = rememberNavController(),
): AppState {
    return remember(navHostController) {
        AppState(navHostController)
    }
}
