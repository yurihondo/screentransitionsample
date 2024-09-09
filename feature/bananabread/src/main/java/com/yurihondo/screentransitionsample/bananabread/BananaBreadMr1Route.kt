package com.yurihondo.screentransitionsample.bananabread

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.parameters.DeepLink
import com.yurihondo.screentransitionsample.bananabread.navigation.BananaBreadMr1Graph

@Destination<BananaBreadMr1Graph>(
    start = true,
    deepLinks = [
        DeepLink(
            uriPattern = "https://com.yurihondo.screentransitionsample/bananabread_mr1",
        )
    ]
)
@Composable
internal fun BananaBreadMr1Route(
) {
    BananaBreadMr1Screen()
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
