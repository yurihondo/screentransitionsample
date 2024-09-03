package com.yurihondo.applepie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.yurihondo.applepie.ApplePieMr1Route
import com.yurihondo.applepie.ApplePieRoute

const val applePieNavigationRoute = "apple_pie_route"
const val applePieGraphRoutePattern = "apple_pie_graph"
const val uri_for_root = "https://com.yurihondo.applepie"

internal fun NavController.navigateToApplePieGraph(navOptions: NavOptions? = null) {
    this.navigate(applePieGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.applePieGraph(
    navController: NavController,
    navigator: ApplePieNavigator,
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
            navigateToBananaBreadMr1Graph = navigator::navigateToBananaBreadMr1Graph,
        )
    }
}

private const val applePieMr1GraphRoutePattern = "apple_pie_mr1_graph"
private const val applePieMr1NavigationRouteBase = "apple_pie_mr1_route"
private const val applePieMr1NavigationParamFrom = "from"
const val applePieMr1NavigationRoute =
    "$applePieMr1NavigationRouteBase/{$applePieMr1NavigationParamFrom}"
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
        route = "$applePieMr1GraphRoutePattern/{$applePieMr1NavigationParamFrom}",
        startDestination = applePieMr1NavigationRoute,
    ) {
        composable(
            route = applePieMr1NavigationRoute,
            deepLinks = listOf(
                navDeepLink { uriPattern = "$uri_for_mr1/{$applePieMr1NavigationParamFrom}" }
            ),
        ) { entry ->
            ApplePieMr1Route(
                from = entry.arguments?.getString(applePieMr1NavigationParamFrom) ?: "unknown",
                onClickMoveBananaBreadMr1 = navigateToBananaBreadMr1Graph
            )
        }
    }
}

