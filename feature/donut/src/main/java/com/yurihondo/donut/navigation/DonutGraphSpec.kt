package com.yurihondo.donut.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.core.ui.navigation.GraphSpec

class DonutGraphSpec: GraphSpec {
    override val startRoute = donutNavigationRoute

    override fun NavController.navigateToGraph(navOptions: NavOptions?) {
        navigateToDonutGraph(navOptions)
    }
}
