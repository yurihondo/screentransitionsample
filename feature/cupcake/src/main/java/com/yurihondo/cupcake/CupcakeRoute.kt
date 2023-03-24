package com.yurihondo.cupcake

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun CupcakeRoute(
    modifier: Modifier = Modifier,
) {
    CupcakeScreen(modifier = modifier)
}

@Composable
private fun CupcakeScreen(
    modifier: Modifier = Modifier,
) {
    // Simple screen with a title
    Box(modifier = modifier) {
        Text(text = "Cupcake")
    }
}
