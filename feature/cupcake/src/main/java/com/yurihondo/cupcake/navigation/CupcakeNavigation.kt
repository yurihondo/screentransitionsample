package com.yurihondo.cupcake.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yurihondo.cupcake.CupcakeRoute

const val cupcakeNavigationRoute = "cupcake_route"
const val cupcakeGraphRoutePattern = "cupcake_graph"

fun NavController.navigateToCupcakeGraph(navOptions: NavOptions? = null) {
    this.navigate(cupcakeGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.cupcakeGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = cupcakeGraphRoutePattern,
        startDestination = cupcakeNavigationRoute,
    ) {
        composable(route = cupcakeNavigationRoute) {
            CupcakeRoute()
        }
        nestedGraphs()
    }
}
