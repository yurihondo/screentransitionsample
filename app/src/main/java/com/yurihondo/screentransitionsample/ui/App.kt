package com.yurihondo.screentransitionsample.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.yurihondo.screentransitionsample.navigation.SampleNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App(
    appState: AppState = rememberAppState(
        navHostController = rememberNavController(),
        coroutineScope = rememberCoroutineScope()
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
                    onClickItem = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentTopLevelDestination,
                )
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
        ) { paddingValues ->
            SampleNavHost(
                navHostController = appState.navHostController,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}