package com.yurihondo.screentransitionsample.core.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlin.reflect.KClass

interface GraphSpec {
    val startRouteClass: KClass<out Any>
    fun NavController.navigateToGraph(navOptions: NavOptions? = null)
}
