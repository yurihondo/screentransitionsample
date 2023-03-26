package com.yurihondo.bananabread

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun BananaBreadRoute(
    onClickMoveBananaBreadMr1: () -> Unit,
    onClickJumpApplePirMr1: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BananaBreadScreen(
        onClickMoveBananaBreadMr1 = onClickMoveBananaBreadMr1,
        onClickJumpApplePirMr1 = onClickJumpApplePirMr1,
        modifier = modifier,
    )
}

@Composable
internal fun BananaBreadScreen(
    onClickMoveBananaBreadMr1: () -> Unit,
    onClickJumpApplePirMr1: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Banana Bread")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onClickMoveBananaBreadMr1) {
            Text(text = "Move to MR1")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onClickJumpApplePirMr1) {
            Text(text = "Jump to ApplePie MR1")
        }
    }
}