package com.yurihondo.donut.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.core.ui.navigation.GraphSpec

class DonutGraphSpec: GraphSpec {
    override val startRoute = donutGraphRoutePattern

    override fun NavController.navigate(navOptions: NavOptions?) {
        navigateToDonutGraph(navOptions)
    }
}
