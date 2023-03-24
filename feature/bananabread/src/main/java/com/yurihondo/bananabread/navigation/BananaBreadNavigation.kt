package com.yurihondo.bananabread.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yurihondo.bananabread.BananaBreadRoute

const val bananaBreadNavigationRoute = "banana_bread_route"
const val bananaBreadGraphRoutePattern = "banana_bread_graph"

fun NavController.navigateToBananaBreadGraph(navOptions: NavOptions ? = null) {
    this.navigate(bananaBreadGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.bananaBreadGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = bananaBreadGraphRoutePattern,
        startDestination = bananaBreadNavigationRoute,
    ) {
        composable(route = bananaBreadNavigationRoute) {
            BananaBreadRoute()
        }
        nestedGraphs()
    }
}