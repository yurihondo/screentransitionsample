package com.yurihondo.bananabread.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.yurihondo.bananabread.BananaBreadMr1Route
import com.yurihondo.bananabread.BananaBreadRoute

internal const val bananaBreadNavigationRoute = "banana_bread_route"
internal const val bananaBreadGraphRoutePattern = "banana_bread_graph"

internal fun NavController.navigateToBananaBreadGraph(navOptions: NavOptions? = null) {
    this.navigate(BananaBreadGraph, navOptions)
}

fun NavGraphBuilder.bananaBreadGraph(
    navController: NavController,
    navigator: BananaBreadNavigator,
) {
    navigation<BananaBreadGraph>(
        startDestination = BananaBreadDestination,
    ) {
        composable<BananaBreadDestination> {
            BananaBreadRoute(
                onClickMoveBananaBreadMr1 = navController::navigateToBananaBreadMr1Graph,
                onClickJumpApplePirMr1 = navigator::navigateToApplePieMr1Graph
            )
        }
        bananaBreadMr1Graph()
    }
}

const val bananaBreadMr1NavigationRoute = "banana_bread_mr1_route"
const val bananaBreadMr1GraphRoutePattern = "banana_bread_mr1_graph"
const val uri_for_mr1 = "https://com.yurihondo.bananabread.mr1"

fun NavController.navigateToBananaBreadMr1Graph(navOptions: NavOptions? = null) {
    this.navigate(bananaBreadMr1GraphRoutePattern, navOptions)
}

fun NavGraphBuilder.bananaBreadMr1Graph(
) {
    navigation<BananaBreadMr1Graph>(
        startDestination = BananaBreadMr1Destination,
    ) {
        composable<BananaBreadMr1Destination>(
            deepLinks = listOf(
                navDeepLink { uriPattern = uri_for_mr1 }
            ),
        ) {
            BananaBreadMr1Route()
        }
    }
}
