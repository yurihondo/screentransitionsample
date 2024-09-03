package com.yurihondo.applepie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.core.ui.navigation.GraphSpec

class AppleGraphSpec : GraphSpec {

    override val startRoute: String = applePieGraphRoutePattern

    override fun NavController.navigate(navOptions: NavOptions?) {
        navigateToApplePieGraph()
    }
}
