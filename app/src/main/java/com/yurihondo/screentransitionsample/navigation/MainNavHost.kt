package com.yurihondo.screentransitionsample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.yurihondo.applepie.navigation.applePieGraph
import com.yurihondo.applepie.navigation.applePieGraphRoutePattern
import com.yurihondo.applepie.navigation.navigateToApplePieMr1Graph
import com.yurihondo.bananabread.navigation.bananaBreadGraph
import com.yurihondo.cupcake.navigation.cupcakeGraph
import com.yurihondo.donut.navigation.donutGraph
import com.yurihondo.eclair.navigation.eclairGraph

@Composable
internal fun MainNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val mainNavigator = rememberMainNavigator(navHostController)

    NavHost(
        navController = navHostController,
        startDestination = applePieGraphRoutePattern,
        modifier = modifier,
    ) {
        applePieGraph(
            navController = navHostController,
            navigator = mainNavigator,
        )
        bananaBreadGraph(
            navController = navHostController,
            navigator = mainNavigator,
        )
        cupcakeGraph(
            navigator = mainNavigator,
        )
        donutGraph {}
        eclairGraph {}
    }
}
