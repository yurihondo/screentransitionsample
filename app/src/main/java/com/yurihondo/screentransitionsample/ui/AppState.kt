package com.yurihondo.screentransitionsample.ui

import androidx.compose.runtime.*
import com.yurihondo.screentransitionsample.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Stable
class AppState(
    val coroutineScope: CoroutineScope,
) {
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    var currentTopLevelDestination by mutableStateOf(TopLevelDestination.APPLE_PIE)
        private set

    fun navigateToTopLevelDestination(destination: TopLevelDestination) {
        currentTopLevelDestination = destination
    }
}

@Composable
fun rememberAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): AppState {
    return remember(coroutineScope) {
        AppState(coroutineScope)
    }
}
