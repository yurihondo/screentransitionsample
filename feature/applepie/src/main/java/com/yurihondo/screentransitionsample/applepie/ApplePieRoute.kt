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
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yurihondo.screentransitionsample.applepie.destinations.ApplePieMr1RouteDestination
import com.yurihondo.screentransitionsample.applepie.navigation.ApplePieGraph

@Destination<ApplePieGraph>(
    start = true,
    deepLinks = [
        DeepLink(
            uriPattern = "https://com.yurihondo.screentransitionsample/applepie",
        )
    ]
)
@Composable
internal fun ApplePieRoute(
    destinationsNavigator: DestinationsNavigator,
) {
    ApplePieScreen(
        onNavigateApplePieMr1 = { from ->
            destinationsNavigator.navigate(
                ApplePieMr1RouteDestination(from)
            )
        },
    )
}

@Composable
private fun ApplePieScreen(
    onNavigateApplePieMr1: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Apple Pie")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { onNavigateApplePieMr1("ApplePie") }) {
            Text(text = "Move to MR1")
        }
    }
}
