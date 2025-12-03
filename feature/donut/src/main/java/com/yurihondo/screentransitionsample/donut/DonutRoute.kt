package com.yurihondo.screentransitionsample.donut

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DonutRoute(
    modifier: Modifier = Modifier,
) {
    DonutScreen(modifier = modifier)
}

@Composable
internal fun DonutScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.statusBarsPadding()) {
        Text(text = "Donut")
    }
}
