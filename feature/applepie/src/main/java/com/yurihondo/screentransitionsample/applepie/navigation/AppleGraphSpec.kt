package com.yurihondo.screentransitionsample.applepie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.screentransitionsample.core.ui.navigation.GraphSpec

class AppleGraphSpec : GraphSpec {

    override val startRoute: String = applePieNavigationRoute

    override fun NavController.navigate(navOptions: NavOptions?) {
        navigateToApplePieGraph(navOptions)
    }
}
