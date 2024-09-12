package com.yurihondo.screentransitionsample.applepie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.yurihondo.applepie.navigation.ApplePieDestination
import com.yurihondo.applepie.navigation.ApplePieGraph
import com.yurihondo.applepie.navigation.ApplePieMr1Destination
import com.yurihondo.applepie.navigation.ApplePieMr1Graph
import com.yurihondo.applepie.navigation.EditDestination
import com.yurihondo.screentransitionsample.applepie.ApplePieMr1Route
import com.yurihondo.screentransitionsample.applepie.ApplePieRoute
import com.yurihondo.screentransitionsample.applepie.EditRoute

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
                onClick = { navController.navigate(ApplePieMr1Destination("ApplePie")) }
            )
        }
        applePieMr1Graph(
            navController = navController,
            navigateToBananaBreadMr1Graph = navigator::navigateToBananaBreadMr1,
        )
    }
}

fun NavGraphBuilder.applePieMr1Graph(
    navController: NavController,
    navigateToBananaBreadMr1Graph: () -> Unit,
) {
    navigation<ApplePieMr1Graph>(
        startDestination = ApplePieMr1Destination("unknown"),
    ) {
        composable<ApplePieMr1Destination>(
            deepLinks = listOf(
                navDeepLink<ApplePieMr1Destination>(
                    basePath = "https://com.yurihondo.screentransitionsample/applepie_mr1"
                )
            ),
        ) { backStackEntry ->
            val from = backStackEntry.savedStateHandle.get<String>(EditDestination.RESULT_KEY_FROM)
                ?: backStackEntry.toRoute<ApplePieMr1Destination>().from
            ApplePieMr1Route(
                from = from,
                onClickMoveBananaBreadMr1 = navigateToBananaBreadMr1Graph,
                onNavigateEdit = { navController.navigate(EditDestination(from)) }
            )
        }

        composable<EditDestination> { backStackEntry ->
            EditRoute(
                from = backStackEntry.toRoute<EditDestination>().from,
                onDone = {
                    navController.previousBackStackEntry?.savedStateHandle?.set(EditDestination.RESULT_KEY_FROM, it)
                    navController.popBackStack()
                }
            )
        }
    }
}
