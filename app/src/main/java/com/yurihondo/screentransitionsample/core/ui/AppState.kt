package com.yurihondo.screentransitionsample.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavKey
import com.yurihondo.applepie.navigation.ApplePie
import com.yurihondo.applepie.navigation.ApplePieMr1
import com.yurihondo.bananabread.navigation.BananaBread
import com.yurihondo.bananabread.navigation.BananaBreadMr1
import com.yurihondo.cupcake.navigation.Cupcake
import com.yurihondo.eclair.navigation.Eclair
import com.yurihondo.screentransitionsample.core.common.extension.findActivity
import com.yurihondo.screentransitionsample.donut.navigation.Donut
import com.yurihondo.screentransitionsample.navigation.LifoUniqueQueue
import com.yurihondo.screentransitionsample.navigation.NavigationState
import com.yurihondo.screentransitionsample.navigation.TopLevelDestination
import com.yurihondo.screentransitionsample.navigation.TopLevelDestinationBehavior
import com.yurihondo.screentransitionsample.navigation.TopLevelDestinationBehavior.HIDE
import com.yurihondo.screentransitionsample.navigation.TopLevelDestinationBehavior.SAME_AS_PARENT

@Stable
internal class AppState(
    private val coreData: AppStateCoreData,
    val navigationState: NavigationState,
) {

    companion object {
        private val DEFAULT_DESTINATION = TopLevelDestination.APPLE_PIE

        private val TOP_LEVEL_ROUTES = setOf<NavKey>(
            ApplePie,
            BananaBread,
            Cupcake,
            Donut,
            Eclair,
        )

        internal val TOP_LEVEL_NAVIGATION_BEHAVIOR_MAP = mapOf(
            ApplePieMr1::class to HIDE,
            BananaBreadMr1::class to SAME_AS_PARENT,
        )
    }

    val topLevelDestinations: List<TopLevelDestination> =
        TopLevelDestination.getAvailableDestinations()

    val currentTopLevelDestination
        get() = coreData.currentTopLevelDestination

    var shouldShowNavigation by mutableStateOf(true)
        private set

    init {
        if (coreData.currentTopLevelDestination == TopLevelDestination.UNKNOWN) {
            coreData.currentTopLevelDestination = DEFAULT_DESTINATION
        }

        coreData.topLevelDestinationBackQueue.add(currentTopLevelDestination)
    }

    fun onSelectTopLevelDestination(destination: TopLevelDestination) {
        navigationState.switchTopLevelRoute(destination.startRoute)
        coreData.topLevelDestinationBackQueue.add(destination)
        coreData.currentTopLevelDestination = destination
    }

    fun navigate(route: NavKey) {
        navigationState.navigate(route)
        updateNavigationVisibility(route)
    }

    fun onBack(finishActivity: () -> Unit) {
        if (navigationState.isAtStartRoute()) {
            // At start of current tab - check tab history
            coreData.topLevelDestinationBackQueue.remove()
            coreData.topLevelDestinationBackQueue.element()?.let { dest ->
                navigationState.switchTopLevelRoute(dest.startRoute)
                coreData.currentTopLevelDestination = dest
                showNavigation()
            } ?: finishActivity()
        } else {
            navigationState.popBackStack()
            // Update visibility based on current route
            val currentRoute = navigationState.currentBackStack().lastOrNull()
            if (currentRoute != null) {
                updateNavigationVisibility(currentRoute)
            } else {
                showNavigation()
            }
        }
    }

    fun onHandleDeepLinksIntent() {
        hideNavigation()
    }

    private fun updateNavigationVisibility(route: NavKey) {
        val behaviorType = TOP_LEVEL_NAVIGATION_BEHAVIOR_MAP[route::class]
        when (behaviorType) {
            HIDE -> hideNavigation()
            SAME_AS_PARENT -> {
                // Keep current visibility
            }
            else -> showNavigation()
        }
    }

    private fun hideNavigation() {
        shouldShowNavigation = false
    }

    private fun showNavigation() {
        shouldShowNavigation = true
    }
}

@Stable
internal class AppStateCoreData(
    currentTopLevelDestination: TopLevelDestination = TopLevelDestination.UNKNOWN,
    val topLevelDestinationBackQueue: LifoUniqueQueue<TopLevelDestination> = LifoUniqueQueue(),
) {
    var currentTopLevelDestination: TopLevelDestination by mutableStateOf(currentTopLevelDestination)

    companion object {
        private const val KEY_CURRENT_TOP_LEVEL_DESTINATION = "key_current_top_level_destination"
        private const val KEY_TOP_LEVEL_DESTINATION_BACK_QUEUE =
            "key_top_level_destination_back_queue"

        @Suppress("UNCHECKED_CAST")
        val Saver = mapSaver(
            save = {
                mapOf(
                    KEY_CURRENT_TOP_LEVEL_DESTINATION to it.currentTopLevelDestination,
                    KEY_TOP_LEVEL_DESTINATION_BACK_QUEUE to it.topLevelDestinationBackQueue.toSet(),
                )
            },
            restore = {
                AppStateCoreData(
                    currentTopLevelDestination = it[KEY_CURRENT_TOP_LEVEL_DESTINATION] as TopLevelDestination,
                    topLevelDestinationBackQueue = LifoUniqueQueue(
                        it[KEY_TOP_LEVEL_DESTINATION_BACK_QUEUE] as Set<TopLevelDestination>
                    ),
                )
            }
        )
    }
}

@Composable
internal fun rememberAppState(): AppState {
    val coreData = rememberSaveable(saver = AppStateCoreData.Saver) { AppStateCoreData() }

    val topLevelRoutes = remember {
        setOf<NavKey>(ApplePie, BananaBread, Cupcake, Donut, Eclair)
    }

    val navigationState = remember {
        NavigationState(
            startRoute = coreData.currentTopLevelDestination.startRoute,
            topLevelRoutes = topLevelRoutes,
        )
    }

    return remember(coreData, navigationState) {
        AppState(
            coreData = coreData,
            navigationState = navigationState,
        )
    }
}
