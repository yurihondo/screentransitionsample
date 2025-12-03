package com.yurihondo.screentransitionsample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.yurihondo.screentransitionsample.donut.DonutMr1Route
import com.yurihondo.screentransitionsample.donut.DonutMr1ViewModel
import com.yurihondo.screentransitionsample.donut.DonutRoute
import com.yurihondo.screentransitionsample.donut.navigation.Donut
import com.yurihondo.screentransitionsample.donut.navigation.DonutMr1
import com.yurihondo.screentransitionsample.eclair.EclairRoute

private const val EDIT_RESULT_KEY = "edit_result"
private const val DONUT_EDIT_RESULT_KEY = "donut_edit_result"

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

        // Remember the consumed result to avoid losing it on recomposition
        var consumedResult by remember { mutableStateOf<String?>(null) }

        // When we get a new result, consume it via LaunchedEffect (not during composition)
        LaunchedEffect(editResult) {
            if (editResult != null) {
                consumedResult = editResult
                // Clear from store after capturing
                resultStore.removeResult(EDIT_RESULT_KEY)
            }
        }

        val currentFrom = consumedResult ?: key.from

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
                // Determine which result key to use based on source
                val resultKey = if (key.from == "Donut") {
                    DONUT_EDIT_RESULT_KEY
                } else {
                    EDIT_RESULT_KEY
                }
                // Set result to ResultStore before navigating back
                resultStore.setResult(result, resultKey)
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
        // Listen for edit result from Donut's Edit flow
        val donutEditResult by resultStore.getResultState<String>(DONUT_EDIT_RESULT_KEY)

        // When result is available, navigate to DonutMr1
        LaunchedEffect(donutEditResult) {
            if (donutEditResult != null) {
                val result = donutEditResult!!
                resultStore.removeResult(DONUT_EDIT_RESULT_KEY)
                appState.navigate(DonutMr1(result))
            }
        }

        DonutRoute(
            onNavigateEdit = { appState.navigate(Edit("Donut")) }
        )
    }

    entry<DonutMr1> { key ->
        val viewModel = viewModel<DonutMr1ViewModel> {
            DonutMr1ViewModel(key)
        }
        DonutMr1Route(result = viewModel.result)
    }

    // Eclair feature
    entry<Eclair> {
        EclairRoute()
    }
}
