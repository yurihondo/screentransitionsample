package com.yurihondo.applepie.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.yurihondo.applepie.ApplePieMr1Route
import com.yurihondo.applepie.ApplePieRoute

const val applePieNavigationRoute = "apple_pie_route"
const val applePieGraphRoutePattern = "apple_pie_graph"
const val uri_for_root = "https://com.yurihondo.applepie"


fun NavController.navigateToApplePieGraph(navOptions: NavOptions? = null) {
    this.navigate(applePieGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.applePieGraph(
    navController: NavController,
    navigateToBananaBreadMr1Graph: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = applePieGraphRoutePattern,
        startDestination = applePieNavigationRoute,
    ) {
        composable(
            route = applePieNavigationRoute,
            deepLinks = listOf(
                navDeepLink { uriPattern = uri_for_root }
            ),
        ) {
            ApplePieRoute(
                onClick = { navController.navigateToApplePieMr1Graph("ApplePie") }
            )
        }
        applePieMr1Graph(
            navigateToBananaBreadMr1Graph = navigateToBananaBreadMr1Graph,
        )
        nestedGraphs()
    }
}

const val applePieMr1NavigationRoute = "apple_pie_mr1_route"
const val applePieMr1GraphRoutePattern = "apple_pie_mr1_graph"
const val uri_for_mr1 = "https://com.yurihondo.applepie.mr1"

fun NavController.navigateToApplePieMr1Graph(
    from: String,
    navOptions: NavOptions? = null
) {
    this.navigate("$applePieMr1GraphRoutePattern/$from", navOptions)
}

fun NavGraphBuilder.applePieMr1Graph(
    navigateToBananaBreadMr1Graph: () -> Unit,
) {
    navigation(
        route = "$applePieMr1GraphRoutePattern/{from}",
        startDestination = "$applePieMr1NavigationRoute/{from}",
    ) {
        composable(
            route = "$applePieMr1NavigationRoute/{from}",
            deepLinks = listOf(
                navDeepLink { uriPattern = "$uri_for_mr1/{from}" }
            ),
        ) { entry ->
            ApplePieMr1Route(
                from = entry.arguments?.getString("from") ?: "unknown",
                onClickMoveBananaBreadMr1 = navigateToBananaBreadMr1Graph
            )
        }
    }
}

