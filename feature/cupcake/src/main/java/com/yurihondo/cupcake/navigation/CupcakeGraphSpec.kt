package com.yurihondo.cupcake.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.core.ui.navigation.GraphSpec

class CupcakeGraphSpec: GraphSpec {
    override val startRoute = cupcakeNavigationRoute

    override fun NavController.navigate(navOptions: NavOptions?) {
        navigateToCupcakeGraph(navOptions)
    }
}
