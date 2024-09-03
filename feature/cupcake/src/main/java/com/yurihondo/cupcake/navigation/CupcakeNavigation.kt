package com.yurihondo.cupcake.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yurihondo.cupcake.CupcakeRoute

internal const val cupcakeNavigationRoute = "cupcake_route"
internal const val cupcakeGraphRoutePattern = "cupcake_graph"

internal fun NavController.navigateToCupcakeGraph(navOptions: NavOptions? = null) {
    this.navigate(cupcakeGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.cupcakeGraph(
    navigator: CupcakeNavigator,
) {
    navigation(
        route = cupcakeGraphRoutePattern,
        startDestination = cupcakeNavigationRoute,
    ) {
        composable(route = cupcakeNavigationRoute) {
            CupcakeRoute(
                onNavigateToApplePieMr1 = navigator::navigateToApplePieMr1Graph
            )
        }
    }
}
