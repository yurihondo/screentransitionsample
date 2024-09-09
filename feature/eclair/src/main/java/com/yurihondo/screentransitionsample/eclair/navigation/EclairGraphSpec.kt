package com.yurihondo.screentransitionsample.eclair.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.screentransitionsample.core.ui.navigation.GraphSpec

class EclairGraphSpec: GraphSpec{
    override val startRoute = eclairNavigationRoute

    override fun NavController.navigate(navOptions: NavOptions?) {
        navigateToEclairGraph(navOptions)
    }
}
