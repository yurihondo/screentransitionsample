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
import com.yurihondo.screentransitionsample.navigation.TopLevelDestination

@Stable
internal class AppState(
    val navHostController: NavHostController,
) {
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    var currentTopLevelDestination by mutableStateOf(TopLevelDestination.APPLE_PIE)
        private set

    fun onSelectTopLevelDestination(destination: TopLevelDestination) {
        navigateToTopLevelDestination(destination)
        currentTopLevelDestination = destination
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
}

@Composable
internal fun rememberAppState(
    navHostController: NavHostController = rememberNavController(),
): AppState {
    return remember(navHostController) {
        AppState(navHostController)
    }
}
