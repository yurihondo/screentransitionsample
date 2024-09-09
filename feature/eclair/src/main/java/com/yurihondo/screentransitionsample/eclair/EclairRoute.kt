package com.yurihondo.screentransitionsample.eclair

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.yurihondo.screentransitionsample.eclair.navigation.EclairGraph

@Destination<EclairGraph>(start = true)
@Composable
internal fun EclairRoute(
) {
    EclairScreen()
}

@Composable
internal fun EclairScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(text = "Eclair")
    }
}
