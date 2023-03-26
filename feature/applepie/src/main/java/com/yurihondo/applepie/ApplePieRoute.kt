package com.yurihondo.applepie

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ApplePieRoute(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    ApplePieScreen(
        modifier = modifier,
        onClick = onClick,
    )
}

@Composable
private fun ApplePieScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Apple Pie")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onClick) {
            Text(text = "Move to MR1")
        }
    }
}
