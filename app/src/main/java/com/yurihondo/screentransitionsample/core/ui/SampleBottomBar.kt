package com.yurihondo.screentransitionsample.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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

private const val DurationForTopLevelNavigationVisibilityChangeMillis = 200

@Composable
internal fun SampleBottomBar(
    destinations: List<TopLevelDestination>,
    onClickItem: (TopLevelDestination) -> Unit,
    currentDestination: TopLevelDestination?,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            animationSpec = tween(
                durationMillis = DurationForTopLevelNavigationVisibilityChangeMillis,
                easing = LinearOutSlowInEasing
            ),
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            animationSpec = tween(
                durationMillis = DurationForTopLevelNavigationVisibilityChangeMillis,
                easing = LinearOutSlowInEasing
            ),
            targetOffsetY = { it }
        ),
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
