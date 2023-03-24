package com.yurihondo.bananabread

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun BananaBreadRoute(
    modifier: Modifier = Modifier,
) {
    BananaBreadScreen(modifier = modifier)
}

@Composable
internal fun BananaBreadScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(text = "Banana Bread")
    }
}