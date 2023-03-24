package com.yurihondo.screentransitionsample.ui

import androidx.compose.runtime.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.yurihondo.applepie.navigation.navigateToApplePieGraph
import com.yurihondo.bananabread.navigation.navigateToBananaBreadGraph
import com.yurihondo.cupcake.navigation.navigateToCupcakeGraph
import com.yurihondo.donut.navigation.navigateToDonutGraph
import com.yurihondo.eclair.navigation.navigateToEclairGraph
import com.yurihondo.screentransitionsample.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Stable
class AppState(
    val navHostController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    var currentTopLevelDestination by mutableStateOf(TopLevelDestination.APPLE_PIE)
        private set

    fun navigateToTopLevelDestination(destination: TopLevelDestination) {
        navOptions {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }.let(
            with(navHostController) {
                return@with when (destination) {
                    TopLevelDestination.APPLE_PIE -> ::navigateToApplePieGraph
                    TopLevelDestination.BANANA_BREAD -> ::navigateToBananaBreadGraph
                    TopLevelDestination.CUPCAKE -> ::navigateToCupcakeGraph
                    TopLevelDestination.DONUT -> ::navigateToDonutGraph
                    TopLevelDestination.ECLAIR -> ::navigateToEclairGraph
                }
            }
        )
        currentTopLevelDestination = destination
    }
}

@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): AppState {
    return remember(navHostController, coroutineScope) {
        AppState(navHostController, coroutineScope)
    }
}
