package com.yurihondo.applepie

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ApplePieMr1Route(
    from: String,
    onClickMoveBananaBreadMr1: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ApplePieMr1Screen(
        from = from,
        onClickMoveBananaBreadMr1 = onClickMoveBananaBreadMr1,
        modifier = modifier,
    )
}

@Composable
private fun ApplePieMr1Screen(
    from: String,
    onClickMoveBananaBreadMr1: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Apple Pie MR1 from $from")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onClickMoveBananaBreadMr1) {
            Text(text = "Jump to Banana Bread MR1")
        }
    }
}
