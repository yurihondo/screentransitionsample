package com.yurihondo.applepie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.yurihondo.core.ui.navigation.GraphSpec

class AppleGraphSpec : GraphSpec {

    override val startRouteClass = ApplePieDestination::class

    override fun NavController.navigateToGraph(navOptions: NavOptions?) {
        navigateToApplePieGraph(navOptions)
    }
}
