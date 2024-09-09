package com.yurihondo.screentransitionsample.eclair

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun EclairRoute(
    modifier: Modifier = Modifier,
) {
    EclairScreen(modifier = modifier)
}

@Composable
internal fun EclairScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(text = "Eclair")
    }
}
