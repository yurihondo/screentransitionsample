package com.yurihondo.screentransitionsample.cupcake.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.screentransitionsample.core.ui.navigation.GraphSpec

class CupcakeGraphSpec: GraphSpec {
    override val startRoute = cupcakeNavigationRoute

    override fun NavController.navigate(navOptions: NavOptions?) {
        navigateToCupcakeGraph(navOptions)
    }
}
