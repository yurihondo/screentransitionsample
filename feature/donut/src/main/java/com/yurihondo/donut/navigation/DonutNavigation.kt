package com.yurihondo.donut.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yurihondo.donut.DonutRoute

internal fun NavController.navigateToDonutGraph(navOptions: NavOptions ? = null) {
    this.navigate(DonutGraph, navOptions)
}

fun NavGraphBuilder.donutGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation<DonutGraph>(
        startDestination = DonutDestination,
    ) {
        composable<DonutDestination> {
            DonutRoute()
        }
        nestedGraphs()
    }
}
