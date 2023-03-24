package com.yurihondo.applepie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yurihondo.applepie.ApplePieRoute

const val applePieNavigationRoute = "apple_pie_route"
const val applePieGraphRoutePattern = "apple_pie_graph"

fun NavController.navigateToApplePieGraph(navOptions: NavOptions ? = null) {
    this.navigate(applePieGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.applePieGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = applePieGraphRoutePattern,
        startDestination = applePieNavigationRoute,
    ) {
        composable(route = applePieNavigationRoute) {
            ApplePieRoute()
        }
        nestedGraphs()
    }
}
