package com.yurihondo.screentransitionsample.cupcake

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
import com.yurihondo.screentransitionsample.cupcake.navigation.CupcakeGraph
import com.yurihondo.screentransitionsample.cupcake.navigation.CupcakeNavigator

@Destination<CupcakeGraph>(start = true)
@Composable
internal fun CupcakeRoute(
    cupcakeNavigator: CupcakeNavigator,
) {
    CupcakeScreen(
        onNavigateToApplePieMr1 = cupcakeNavigator::navigateToApplePieMr1Graph,
    )
}

@Composable
private fun CupcakeScreen(
    onNavigateToApplePieMr1: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Cupcake")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { onNavigateToApplePieMr1("CupCake") }) {
            Text(text = "Jump to ApplePie MR1")
        }
    }
}
