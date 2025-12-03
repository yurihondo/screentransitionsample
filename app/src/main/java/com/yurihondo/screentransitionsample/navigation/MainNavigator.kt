package com.yurihondo.screentransitionsample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.yurihondo.applepie.navigation.ApplePieMr1
import com.yurihondo.bananabread.navigation.BananaBreadMr1
import com.yurihondo.screentransitionsample.applepie.navigation.ApplePieNavigator
import com.yurihondo.screentransitionsample.bananabread.navigation.BananaBreadNavigator
import com.yurihondo.screentransitionsample.core.ui.AppState
import com.yurihondo.screentransitionsample.cupcake.navigation.CupcakeNavigator

internal class MainNavigator(
    private val appState: AppState,
) : ApplePieNavigator, BananaBreadNavigator, CupcakeNavigator, DeepLinksNavigator {

    override fun navigateToBananaBreadMr1() {
        appState.navigate(BananaBreadMr1)
    }

    override fun navigateToApplePieMr1(from: String) {
        appState.navigate(ApplePieMr1(from))
    }

    override fun navigateToBananaBreadMr1FromExternal(clearStack: Boolean) {
        // For deep links, navigate directly
        // clearStack behavior will be handled by onHandleDeepLinksIntent in AppState
        appState.navigate(BananaBreadMr1)
    }
}

@Composable
internal fun rememberMainNavigator(
    appState: AppState,
): MainNavigator {
    return remember(appState) {
        MainNavigator(appState)
    }
}
