package com.yurihondo.screentransitionsample.donut

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DonutMr1Route(
    result: String,
    modifier: Modifier = Modifier,
) {
    DonutMr1Screen(
        result = result,
        modifier = modifier,
    )
}

@Composable
private fun DonutMr1Screen(
    result: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Donut MR1")
        Text(text = "Result: $result")
    }
}
