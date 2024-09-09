package com.yurihondo.cupcake.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.core.ui.navigation.GraphSpec

class CupcakeGraphSpec: GraphSpec {
    override val startRouteClass = CupcakeDestination::class

    override fun NavController.navigateToGraph(navOptions: NavOptions?) {
        navigateToCupcakeGraph(navOptions)
    }
}
