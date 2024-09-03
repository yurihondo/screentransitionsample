package com.yurihondo.donut.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yurihondo.donut.DonutRoute

const val donutNavigationRoute = "donut_route"
const val donutGraphRoutePattern = "donut_graph"

internal fun NavController.navigateToDonutGraph(navOptions: NavOptions ? = null) {
    this.navigate(donutGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.donutGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = donutGraphRoutePattern,
        startDestination = donutNavigationRoute,
    ) {
        composable(route = donutNavigationRoute) {
            DonutRoute()
        }
        nestedGraphs()
    }
}
