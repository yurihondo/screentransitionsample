package com.yurihondo.screentransitionsample.bananabread

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BananaBreadMr1Route(
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
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Banana Bread MR1")
    }
}
