package com.yurihondo.screentransitionsample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.yurihondo.applepie.navigation.ApplePieNavigator
import com.yurihondo.applepie.navigation.navigateToApplePieMr1Graph
import com.yurihondo.bananabread.navigation.BananaBreadNavigator
import com.yurihondo.bananabread.navigation.navigateToBananaBreadMr1Graph
import com.yurihondo.cupcake.navigation.CupcakeNavigator

internal class MainNavigator(
    private val navController: NavController,
): ApplePieNavigator, BananaBreadNavigator, CupcakeNavigator, DeepLinksNavigator {

    override fun navigateToBananaBreadMr1Graph() {
        navController.navigateToBananaBreadMr1Graph()
    }

    override fun navigateToApplePieMr1Graph(from: String) {
        navController.navigateToApplePieMr1Graph(from)
    }

}

@Composable
internal fun rememberMainNavigator(
    navController: NavController,
): MainNavigator {
    return remember(navController) {
        MainNavigator(navController)
    }
}
