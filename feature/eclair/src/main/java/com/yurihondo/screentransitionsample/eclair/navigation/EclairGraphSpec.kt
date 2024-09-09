package com.yurihondo.screentransitionsample.eclair.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.eclair.navigation.EclairDestination
import com.yurihondo.screentransitionsample.core.ui.navigation.GraphSpec

class EclairGraphSpec: GraphSpec{
    override val startRouteClass = EclairDestination::class

    override fun NavController.navigateToGraph(navOptions: NavOptions?) {
        navigateToEclairGraph(navOptions)
    }
}
