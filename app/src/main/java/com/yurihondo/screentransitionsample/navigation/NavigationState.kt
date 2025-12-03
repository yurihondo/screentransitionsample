package com.yurihondo.screentransitionsample.navigation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

@Stable
internal class NavigationState(
    val startRoute: NavKey,
    val topLevelRoutes: Set<NavKey>,
) {
    private val _topLevelRoute = mutableStateOf(startRoute)
    val topLevelRoute: NavKey get() = _topLevelRoute.value

    val backStacks: Map<NavKey, SnapshotStateList<NavKey>> =
        topLevelRoutes.associateWith { mutableStateListOf(it) }

    fun switchTopLevelRoute(route: NavKey) {
        _topLevelRoute.value = route
    }

    fun currentBackStack(): List<NavKey> = backStacks[topLevelRoute] ?: emptyList()

    fun navigate(route: NavKey) {
        backStacks[topLevelRoute]?.add(route)
    }

    fun popBackStack(): Boolean {
        val stack = backStacks[topLevelRoute] ?: return false
        return if (stack.size > 1) {
            stack.removeAt(stack.lastIndex)
            true
        } else {
            false
        }
    }

    fun isAtStartRoute(): Boolean {
        val stack = backStacks[topLevelRoute] ?: return true
        return stack.size <= 1
    }
}
