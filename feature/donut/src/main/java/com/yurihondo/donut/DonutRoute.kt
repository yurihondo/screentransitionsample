package com.yurihondo.donut

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun DonutRoute(
    modifier: Modifier = Modifier,
) {
    DonutScreen(modifier = modifier)
}

@Composable
internal fun DonutScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(text = "Donut")
    }
}