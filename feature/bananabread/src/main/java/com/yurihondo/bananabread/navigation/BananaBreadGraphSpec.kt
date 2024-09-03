package com.yurihondo.bananabread.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.core.ui.navigation.GraphSpec

class BananaBreadGraphSpec: GraphSpec {
    override val startRoute = bananaBreadNavigationRoute

    override fun NavController.navigate(navOptions: NavOptions?) {
        navigateToBananaBreadGraph(navOptions)
    }
}
