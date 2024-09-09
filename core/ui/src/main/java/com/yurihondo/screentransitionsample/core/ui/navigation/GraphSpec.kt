package com.yurihondo.screentransitionsample.core.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

interface GraphSpec {
    val startRoute: String
    fun NavController.navigate(navOptions: NavOptions? = null)
}
