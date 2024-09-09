package com.yurihondo.screentransitionsample.donut.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.screentransitionsample.core.ui.navigation.GraphSpec

class DonutGraphSpec: GraphSpec {
    override val startRouteClass = DonutDestination::class

    override fun NavController.navigateToGraph(navOptions: NavOptions?) {
        navigateToDonutGraph(navOptions)
    }
}
