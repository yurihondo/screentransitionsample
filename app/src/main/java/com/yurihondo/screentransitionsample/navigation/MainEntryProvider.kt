package com.yurihondo.screentransitionsample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import com.yurihondo.applepie.navigation.ApplePie
import com.yurihondo.applepie.navigation.ApplePieMr1
import com.yurihondo.applepie.navigation.Edit
import com.yurihondo.bananabread.navigation.BananaBread
import com.yurihondo.bananabread.navigation.BananaBreadMr1
import com.yurihondo.cupcake.navigation.Cupcake
import com.yurihondo.eclair.navigation.Eclair
import com.yurihondo.screentransitionsample.applepie.ApplePieMr1Route
import com.yurihondo.screentransitionsample.applepie.ApplePieRoute
import com.yurihondo.screentransitionsample.applepie.EditRoute
import com.yurihondo.screentransitionsample.bananabread.BananaBreadMr1Route
import com.yurihondo.screentransitionsample.bananabread.BananaBreadRoute
import com.yurihondo.screentransitionsample.core.ui.AppState
import com.yurihondo.screentransitionsample.cupcake.CupcakeRoute
import com.yurihondo.screentransitionsample.donut.DonutRoute
import com.yurihondo.screentransitionsample.donut.navigation.Donut
import com.yurihondo.screentransitionsample.eclair.EclairRoute

@Composable
internal fun createMainEntryProvider(
    appState: AppState,
    navigator: MainNavigator,
) = entryProvider<NavKey> {
    // State for result passing (Edit → ApplePieMr1)
    var editResult by remember { mutableStateOf<String?>(null) }

    // ApplePie feature
    entry<ApplePie> {
        ApplePieRoute(
            onClick = { appState.navigate(ApplePieMr1("ApplePie")) }
        )
    }

    entry<ApplePieMr1> { key ->
        val currentFrom = editResult ?: key.from
        // Clear result after consumption
        if (editResult != null) {
            editResult = null
        }

        ApplePieMr1Route(
            from = currentFrom,
            onClickMoveBananaBreadMr1 = { navigator.navigateToBananaBreadMr1() },
            onNavigateEdit = { appState.navigate(Edit(currentFrom)) }
        )
    }

    entry<Edit> { key ->
        EditRoute(
            from = key.from,
            onDone = { result ->
                editResult = result
                appState.onBack {}
            }
        )
    }

    // BananaBread feature
    entry<BananaBread> {
        BananaBreadRoute(
            onClickMoveBananaBreadMr1 = { appState.navigate(BananaBreadMr1) },
            onClickJumpApplePirMr1 = { from -> navigator.navigateToApplePieMr1(from) }
        )
    }

    entry<BananaBreadMr1> {
        BananaBreadMr1Route()
    }

    // Cupcake feature
    entry<Cupcake> {
        CupcakeRoute(
            onNavigateToApplePieMr1 = { from -> navigator.navigateToApplePieMr1(from) }
        )
    }

    // Donut feature
    entry<Donut> {
        DonutRoute()
    }

    // Eclair feature
    entry<Eclair> {
        EclairRoute()
    }
}
