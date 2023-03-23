package com.yurihondo.screentransitionsample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.yurihondo.screentransitionsample.ui.AppState
import com.yurihondo.screentransitionsample.ui.SampleBottomBar
import com.yurihondo.screentransitionsample.ui.rememberAppState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App(
    appState: AppState = rememberAppState(
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
            // Empty screen
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Text for test
                Text(
                    text = "Hello World!",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}