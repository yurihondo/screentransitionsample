package com.yurihondo.screentransitionsample.bananabread.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.bananabread.navigation.BananaBreadDestination
import com.yurihondo.screentransitionsample.core.ui.navigation.GraphSpec

class BananaBreadGraphSpec: GraphSpec {
    override val startRouteClass = BananaBreadDestination::class

    override fun NavController.navigateToGraph(navOptions: NavOptions?) {
        navigateToBananaBreadGraph(navOptions)
    }
}
