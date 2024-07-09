package com.yurihondo.bananabread.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.yurihondo.bananabread.BananaBreadMr1Route
import com.yurihondo.bananabread.BananaBreadRoute

const val bananaBreadNavigationRoute = "banana_bread_route"
const val bananaBreadGraphRoutePattern = "banana_bread_graph"

fun NavController.navigateToBananaBreadGraph(navOptions: NavOptions? = null) {
    this.navigate(bananaBreadGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.bananaBreadGraph(
    navController: NavController,
    navigateToApplePieMr1Graph: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = bananaBreadGraphRoutePattern,
        startDestination = bananaBreadNavigationRoute,
    ) {
        composable(route = bananaBreadNavigationRoute) {
            BananaBreadRoute(
                onClickMoveBananaBreadMr1 = navController::navigateToBananaBreadMr1Graph,
                onClickJumpApplePirMr1 = navigateToApplePieMr1Graph
            )
        }
        bananaBreadMr1Graph()
        nestedGraphs()
    }
}

const val bananaBreadMr1NavigationRoute = "banana_bread_mr1_route"
const val bananaBreadMr1GraphRoutePattern = "banana_bread_mr1_graph"
const val uri_for_mr1 = "https://com.yurihondo.bananabread.mr1"

fun NavController.navigateToBananaBreadMr1Graph(navOptions: NavOptions? = null) {
    this.navigateToBananaBreadGraph()
    this.navigate(bananaBreadMr1GraphRoutePattern, navOptions)
}

fun NavGraphBuilder.bananaBreadMr1Graph(
) {
    navigation(
        route = bananaBreadMr1GraphRoutePattern,
        startDestination = bananaBreadMr1NavigationRoute,
    ) {
        composable(
            route = bananaBreadMr1NavigationRoute,
            deepLinks = listOf(
                navDeepLink { uriPattern = uri_for_mr1 }
            ),
        ) {
            BananaBreadMr1Route()
        }
    }
}
