package com.yurihondo.bananabread.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.yurihondo.bananabread.BananaBreadMr1Route
import com.yurihondo.bananabread.BananaBreadRoute

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

fun NavController.navigateToBananaBreadMr1Graph(navOptions: NavOptions? = null) {
    this.navigate(BananaBreadMr1Destination, navOptions)
}

fun NavGraphBuilder.bananaBreadMr1Graph(
) {
    navigation<BananaBreadMr1Graph>(
        startDestination = BananaBreadMr1Destination,
    ) {
        composable<BananaBreadMr1Destination>(
            deepLinks = listOf(
                navDeepLink<BananaBreadMr1Destination>(
                    basePath = "https://yurihondo.com/bananabread_mr1"
                )
            ),
        ) {
            BananaBreadMr1Route()
        }
    }
}
