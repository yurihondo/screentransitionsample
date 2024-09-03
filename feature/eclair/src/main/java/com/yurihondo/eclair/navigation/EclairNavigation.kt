package com.yurihondo.eclair.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yurihondo.eclair.EclairRoute

const val eclairNavigationRoute = "eclair_route"
const val eclairGraphRoutePattern = "eclair_graph"

internal fun NavController.navigateToEclairGraph(navOptions: NavOptions? = null) {
    this.navigate(eclairGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.eclairGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = eclairGraphRoutePattern,
        startDestination = eclairNavigationRoute,
    ) {
        composable(route = eclairNavigationRoute) {
            EclairRoute()
        }
        nestedGraphs()
    }
}
