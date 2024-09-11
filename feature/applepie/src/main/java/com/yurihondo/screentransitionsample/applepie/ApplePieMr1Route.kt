package com.yurihondo.screentransitionsample.applepie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.parameters.DeepLink
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultRecipient
import com.ramcosta.composedestinations.result.onResult
import com.yurihondo.screentransitionsample.applepie.destinations.EditRouteDestination
import com.yurihondo.screentransitionsample.applepie.navigation.ApplePieMr1Graph
import com.yurihondo.screentransitionsample.applepie.navigation.ApplePieNavigator

data class ApplePieMr1Args(
    val from: String = "unknown",
)

@Destination<ApplePieMr1Graph>(
    start = true,
    navArgs = ApplePieMr1Args::class,
    deepLinks = [
        DeepLink(
            uriPattern = "https://com.yurihondo.screentransitionsample/applepie_mr1?from={from}",
        )
    ],
)
@Composable
internal fun ApplePieMr1Route(
    applePieNavigator: ApplePieNavigator,
    destinationsNavigator: DestinationsNavigator,
    args: ApplePieMr1Args,
    resultRecipient: ResultRecipient<EditRouteDestination, String>
) {
    var from by rememberSaveable { mutableStateOf(args.from) }

    resultRecipient.onResult { editedFrom -> from = editedFrom }

    ApplePieMr1Screen(
        from = from,
        onClickMoveBananaBreadMr1 = applePieNavigator::navigateToBananaBreadMr1,
        onNavigateEdit = { destinationsNavigator.navigate(EditRouteDestination(from)) }
    )
}

@Composable
private fun ApplePieMr1Screen(
    from: String,
    onClickMoveBananaBreadMr1: () -> Unit,
    onNavigateEdit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Apple Pie MR1 from $from")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onClickMoveBananaBreadMr1) {
            Text(text = "Jump to Banana Bread MR1")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onNavigateEdit) {
            Text(text = "Edit")
        }
    }
}
