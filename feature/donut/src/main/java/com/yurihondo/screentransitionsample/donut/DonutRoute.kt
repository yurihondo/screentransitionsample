package com.yurihondo.screentransitionsample.donut

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.yurihondo.screentransitionsample.donut.navigation.DonutGraph

@Destination<DonutGraph>(start = true)
@Composable
internal fun DonutRoute(
) {
    DonutScreen()
}

@Composable
private fun DonutScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(text = "Donut")
    }
}
