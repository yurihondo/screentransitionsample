package com.yurihondo.screentransitionsample.bananabread.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.screentransitionsample.core.ui.navigation.GraphSpec

class BananaBreadGraphSpec: GraphSpec {
    override val startRoute = bananaBreadNavigationRoute

    override fun NavController.navigate(navOptions: NavOptions?) {
        navigateToBananaBreadGraph(navOptions)
    }
}
