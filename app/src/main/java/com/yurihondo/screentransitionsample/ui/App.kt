package com.yurihondo.screentransitionsample.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.yurihondo.screentransitionsample.navigation.MainNavHost

@Composable
internal fun App(
    appState: AppState = rememberAppState(
        navHostController = rememberNavController(),
    )
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Scaffold(
            bottomBar = {
                SampleBottomBar(
                    destinations = appState.topLevelDestinations,
                    onClickItem = appState::onSelectTopLevelDestination,
                    currentDestination = appState.currentTopLevelDestination,
                    isVisible = appState.shouldShowNavigation,
                )
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
        ) { paddingValues ->
            MainNavHost(
                navHostController = appState.navHostController,
                onBack = appState::onBack,
                modifier = Modifier.padding(paddingValues),
                onHandleIntentForDeepLink = appState::onHandleDeepLinksIntent,
            )
        }
    }
}
