package com.yurihondo.screentransitionsample.applepie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.yurihondo.screentransitionsample.applepie.ApplePieMr1Route
import com.yurihondo.screentransitionsample.applepie.ApplePieRoute

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
                onClick = { navController.navigateToApplePieMr1Route("ApplePie") }
            )
        }
        applePieMr1Graph(
            navigateToBananaBreadMr1Graph = navigator::navigateToBananaBreadMr1,
        )
    }
}

private const val applePieMr1GraphRoutePattern = "apple_pie_mr1_graph"
private const val applePieMr1NavigationRouteBase = "apple_pie_mr1_route"
private const val applePieMr1NavigationParamFrom = "from"
const val applePieMr1NavigationRoute =
    "$applePieMr1NavigationRouteBase/{$applePieMr1NavigationParamFrom}"

fun NavController.navigateToApplePieMr1Route(
    from: String,
    navOptions: NavOptions? = null
) {
    this.navigate("$applePieMr1NavigationRouteBase/$from", navOptions)
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
            arguments = listOf(
                navArgument(applePieMr1NavigationParamFrom) { defaultValue = "unknown" }
            ),
            deepLinks = listOf(
                navDeepLink { uriPattern = "https://com.yurihondo.screentransitionsample/applepie_mr1?$applePieMr1NavigationParamFrom={$applePieMr1NavigationParamFrom}" }
            ),
        ) { entry ->
            ApplePieMr1Route(
                from = entry.arguments?.getString(applePieMr1NavigationParamFrom)!!,
                onClickMoveBananaBreadMr1 = navigateToBananaBreadMr1Graph
            )
        }
    }
}
