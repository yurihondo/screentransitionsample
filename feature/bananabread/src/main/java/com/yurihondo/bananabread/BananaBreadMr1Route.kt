package com.yurihondo.bananabread

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun BananaBreadMr1Route(
    modifier: Modifier = Modifier,
) {
    BananaBreadMr1Screen(
        modifier = modifier,
    )
}

@Composable
internal fun BananaBreadMr1Screen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Banana Bread MR1")
    }
}