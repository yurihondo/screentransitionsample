package com.yurihondo.screentransitionsample.applepie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.parameters.DeepLink
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
    args: ApplePieMr1Args,
) {
    ApplePieMr1Screen(
        from = args.from,
        onClickMoveBananaBreadMr1 = applePieNavigator::navigateToBananaBreadMr1Graph,
    )
}

@Composable
private fun ApplePieMr1Screen(
    from: String,
    onClickMoveBananaBreadMr1: () -> Unit,
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
    }
}
