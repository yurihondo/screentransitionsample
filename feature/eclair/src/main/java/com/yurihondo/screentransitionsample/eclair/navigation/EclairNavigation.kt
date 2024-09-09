package com.yurihondo.screentransitionsample.eclair.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yurihondo.eclair.navigation.EclairDestination
import com.yurihondo.eclair.navigation.EclairGraph
import com.yurihondo.screentransitionsample.eclair.EclairRoute

internal fun NavController.navigateToEclairGraph(navOptions: NavOptions? = null) {
    this.navigate(EclairGraph, navOptions)
}

fun NavGraphBuilder.eclairGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation<EclairGraph>(
        startDestination = EclairDestination,
    ) {
        composable<EclairDestination> {
            EclairRoute()
        }
        nestedGraphs()
    }
}
