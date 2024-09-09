package com.yurihondo.cupcake.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yurihondo.cupcake.CupcakeRoute

internal fun NavController.navigateToCupcakeGraph(navOptions: NavOptions? = null) {
    this.navigate(CupcakeGraph, navOptions)
}

fun NavGraphBuilder.cupcakeGraph(
    navigator: CupcakeNavigator,
) {
    navigation<CupcakeGraph>(
        startDestination = CupcakeDestination,
    ) {
        composable<CupcakeDestination> {
            CupcakeRoute(
                onNavigateToApplePieMr1 = navigator::navigateToApplePieMr1Graph
            )
        }
    }
}
