package com.yurihondo.screentransitionsample.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.yurihondo.screentransitionsample.core.common.extension.findActivity
import com.yurihondo.screentransitionsample.navigation.LifoUniqueQueue
import com.yurihondo.screentransitionsample.navigation.TopLevelDestination

@Stable
internal class AppState(
    val navHostController: NavHostController,
) {

    companion object {
    }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    var currentTopLevelDestination by mutableStateOf(TopLevelDestination.APPLE_PIE)
        private set

    private val topLevelDestinationBackQueue: LifoUniqueQueue<TopLevelDestination> = LifoUniqueQueue()

    init {
        topLevelDestinationBackQueue.add(currentTopLevelDestination)
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
}

@Composable
internal fun rememberAppState(
    navHostController: NavHostController = rememberNavController(),
): AppState {
    return remember(navHostController) {
        AppState(navHostController)
    }
}
