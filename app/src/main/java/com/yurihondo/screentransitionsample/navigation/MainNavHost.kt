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
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.navgraphs.MainNavGraph
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.yurihondo.screentransitionsample.core.common.extension.findActivity

@Composable
internal fun MainNavHost(
    navHostController: NavHostController,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onHandleIntentForDeepLink: () -> Unit = {},
) {
    val mainNavigator = rememberMainNavigator(navHostController)

    DestinationsNavHost(
        modifier = modifier,
        navGraph = MainNavGraph,
        navController = navHostController,
        engine = rememberNavHostEngine(),
        dependenciesContainerBuilder = {
            dependency(mainNavigator)
        },
    )

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
