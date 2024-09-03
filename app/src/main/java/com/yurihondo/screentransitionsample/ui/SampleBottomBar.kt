package com.yurihondo.screentransitionsample.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yurihondo.screentransitionsample.navigation.TopLevelDestination

private val BottomNavigationHeight = 72.dp
private const val DurationForTopLevelNavigationVisibilityChangeMillis = 200

@Composable
internal fun SampleBottomBar(
    destinations: List<TopLevelDestination>,
    onClickItem: (TopLevelDestination) -> Unit,
    currentDestination: TopLevelDestination?,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
) {
    val heightDp by animateDpAsState(
        targetValue = if (isVisible) BottomNavigationHeight else 0.dp,
        animationSpec = tween(durationMillis = DurationForTopLevelNavigationVisibilityChangeMillis),
        label = ""
    )

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = DurationForTopLevelNavigationVisibilityChangeMillis,
                easing = LinearOutSlowInEasing
            )
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = DurationForTopLevelNavigationVisibilityChangeMillis,
                easing = LinearOutSlowInEasing
            )
        ),
    ) {
        SampleNavigationBar(
            modifier = modifier.height(heightDp),
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
