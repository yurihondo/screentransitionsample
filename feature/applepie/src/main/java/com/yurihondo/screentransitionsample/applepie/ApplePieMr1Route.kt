package com.yurihondo.screentransitionsample.applepie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
    onNavigateEdit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ApplePieMr1Screen(
        from = from,
        onClickMoveBananaBreadMr1 = onClickMoveBananaBreadMr1,
        onNavigateEdit = onNavigateEdit,
        modifier = modifier,
    )
}

@Composable
private fun ApplePieMr1Screen(
    from: String,
    onClickMoveBananaBreadMr1: () -> Unit,
    onNavigateEdit: () -> Unit,
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
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onNavigateEdit) {
            Text(text = "Edit")
        }
    }
}
