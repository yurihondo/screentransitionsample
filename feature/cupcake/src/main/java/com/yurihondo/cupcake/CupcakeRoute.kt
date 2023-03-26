package com.yurihondo.cupcake

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun CupcakeRoute(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CupcakeScreen(
        onClick = onClick,
        modifier = modifier,
    )
}

@Composable
private fun CupcakeScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Cupcake")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onClick) {
            Text(text = "Jump to ApplePie MR1")
        }
    }
}
