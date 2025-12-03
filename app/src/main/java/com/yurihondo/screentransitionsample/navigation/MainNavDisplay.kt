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
import androidx.navigation3.ui.NavDisplay
import com.yurihondo.screentransitionsample.core.common.extension.findActivity
import com.yurihondo.screentransitionsample.core.ui.AppState

@Composable
internal fun MainNavDisplay(
    appState: AppState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onHandleIntentForDeepLink: () -> Unit,
) {
    val mainNavigator = rememberMainNavigator(appState)
    val entryProvider = createMainEntryProvider(appState, mainNavigator)

    NavDisplay(
        backStack = appState.navigationState.currentBackStack(),
        entryProvider = entryProvider,
        modifier = modifier,
        onBack = onBack,
    )

    // Have to set our own BackHandler after NavDisplay to override the BackHandler inside NavDisplay
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
