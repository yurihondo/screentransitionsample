package com.yurihondo.screentransitionsample.bananabread

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
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yurihondo.screentransitionsample.bananabread.destinations.BananaBreadMr1RouteDestination
import com.yurihondo.screentransitionsample.bananabread.navigation.BananaBreadGraph
import com.yurihondo.screentransitionsample.bananabread.navigation.BananaBreadNavigator

@Destination<BananaBreadGraph>(start = true)
@Composable
internal fun BananaBreadRoute(
    bananaBreadNavigator: BananaBreadNavigator,
    destinationsNavigator: DestinationsNavigator,
) {
    BananaBreadScreen(
        onClickMoveBananaBreadMr1 = { destinationsNavigator.navigate(BananaBreadMr1RouteDestination) },
        onClickJumpApplePirMr1 = { bananaBreadNavigator.navigateToApplePieMr1Graph("BananaBread") },
    )
}

@Composable
internal fun BananaBreadScreen(
    onClickMoveBananaBreadMr1: () -> Unit,
    onClickJumpApplePirMr1: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Banana Bread")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onClickMoveBananaBreadMr1) {
            Text(text = "Move to MR1")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { onClickJumpApplePirMr1("BananaBread") }) {
            Text(text = "Jump to ApplePie MR1")
        }
    }
}
