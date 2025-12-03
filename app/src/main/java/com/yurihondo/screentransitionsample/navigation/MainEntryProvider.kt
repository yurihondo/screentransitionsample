package com.yurihondo.screentransitionsample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

private const val EDIT_RESULT_KEY = "edit_result"

@Composable
internal fun createMainEntryProvider(
    appState: AppState,
    navigator: MainNavigator,
) = entryProvider<NavKey> {
    val resultStore = LocalResultStore.current

    // ApplePie feature
    entry<ApplePie> {
        ApplePieRoute(
            onClick = { appState.navigate(ApplePieMr1("ApplePie")) }
        )
    }

    entry<ApplePieMr1> { key ->
        // Get result from ResultStore (state-based pattern from nav3-recipes)
        val editResult by resultStore.getResultState<String>(EDIT_RESULT_KEY)
        val currentFrom = editResult ?: key.from

        // Clear result after consumption
        if (editResult != null) {
            resultStore.removeResult(EDIT_RESULT_KEY)
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
                // Set result to ResultStore before navigating back
                resultStore.setResult(result, EDIT_RESULT_KEY)
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
