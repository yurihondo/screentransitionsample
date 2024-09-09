package com.yurihondo.screentransitionsample.cupcake.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.cupcake.navigation.CupcakeDestination
import com.yurihondo.screentransitionsample.core.ui.navigation.GraphSpec

class CupcakeGraphSpec: GraphSpec {
    override val startRouteClass = CupcakeDestination::class

    override fun NavController.navigateToGraph(navOptions: NavOptions?) {
        navigateToCupcakeGraph(navOptions)
    }
}
