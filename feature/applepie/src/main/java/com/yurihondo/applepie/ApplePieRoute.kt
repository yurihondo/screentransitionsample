package com.yurihondo.applepie

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ApplePieRoute(
    modifier: Modifier = Modifier,
) {
    ApplePieScreen(modifier = modifier)
}

@Composable
private fun ApplePieScreen(
    modifier: Modifier = Modifier,
) {
    // Simple screen with a title
    Box(modifier = modifier) {
        Text(text = "Apple Pie")
    }
}
