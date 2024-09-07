package com.yurihondo.screentransitionsample.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.yurihondo.applepie.navigation.applePieGraph
import com.yurihondo.applepie.navigation.applePieGraphRoutePattern
import com.yurihondo.bananabread.navigation.bananaBreadGraph
import com.yurihondo.cupcake.navigation.cupcakeGraph
import com.yurihondo.donut.navigation.donutGraph
import com.yurihondo.eclair.navigation.eclairGraph
import com.yurihondo.screentransitionsample.core.common.extension.findActivity

@Composable
internal fun MainNavHost(
    navHostController: NavHostController,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onHandleIntentForDeepLink: () -> Unit,
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

    // Have to set our own BackHandler after NavHost to override the BackHandler inside DestinationsNavHost
    // https://issuetracker.google.com/issues/308445371
    BackHandler(onBack = onBack)

    // Handle deep link
    val context = LocalContext.current
    var isDeepLinkIntentConsumed by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(context) {
        if (isDeepLinkIntentConsumed) return@LaunchedEffect
        mainNavigator.run {
            isDeepLinkIntentConsumed = context.findActivity().intent?.let { intent ->
                handleDeepLinksIfNeeded(intent)
            } ?: false
            if (isDeepLinkIntentConsumed) {
                onHandleIntentForDeepLink()
            }
        }
    }
}
