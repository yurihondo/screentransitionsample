package com.yurihondo.applepie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.yurihondo.applepie.ApplePieMr1Route
import com.yurihondo.applepie.ApplePieRoute
import kotlinx.serialization.Serializable

const val uri_for_root = "https://com.yurihondo.applepie"

internal fun NavController.navigateToApplePieGraph(navOptions: NavOptions? = null) {
    this.navigate(ApplePieGraph, navOptions)
}

fun NavGraphBuilder.applePieGraph(
    navController: NavController,
    navigator: ApplePieNavigator,
) {
    navigation<ApplePieGraph>(
        startDestination = ApplePieDestination,
    ) {
        composable<ApplePieDestination>(
            deepLinks = listOf(
                navDeepLink<ApplePieDestination> (
                    basePath = "https://yurihondo.com/applepie"
                )
            ),
        ) {
            ApplePieRoute(
                onClick = { navController.navigateToApplePieMr1Graph("ApplePie") }
            )
        }
        applePieMr1Graph(
            navigateToBananaBreadMr1Graph = navigator::navigateToBananaBreadMr1Graph,
        )
    }
}

fun NavController.navigateToApplePieMr1Graph(
    from: String,
    navOptions: NavOptions? = null
) {
    this.navigate(ApplePieMr1Destination(from), navOptions)
}

fun NavGraphBuilder.applePieMr1Graph(
    navigateToBananaBreadMr1Graph: () -> Unit,
) {
    navigation<ApplePieMr1Graph>(
        startDestination = ApplePieMr1Destination("unknown"),
    ) {
        composable<ApplePieMr1Destination>(
            deepLinks = listOf(
                navDeepLink<ApplePieMr1Destination>(
                    basePath = "https://yurihondo.com/applepie_mr1"
                )
            ),
        ) { backStackEntry ->
            ApplePieMr1Route(
                from = backStackEntry.toRoute<ApplePieMr1Destination>().from,
                onClickMoveBananaBreadMr1 = navigateToBananaBreadMr1Graph
            )
        }
    }
}
