package com.yurihondo.screentransitionsample.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.yurihondo.screentransitionsample.navigation.TopLevelDestination

@Composable
fun SampleBottomBar(
    destinations: List<TopLevelDestination>,
    onClickItem: (TopLevelDestination) -> Unit,
    currentDestination: TopLevelDestination?,
    modifier: Modifier = Modifier,
) {
    SampleNavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = destination == currentDestination
            SampleBottomBarItem(
                selected = selected,
                onClick = { onClickItem(destination) },
                icon = {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(destination.titleTextId),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
            )
        }
    }
}

@Composable
private fun SampleNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        content = content,
    )
}