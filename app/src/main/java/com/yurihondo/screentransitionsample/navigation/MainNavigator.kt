package com.yurihondo.screentransitionsample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.yurihondo.screentransitionsample.applepie.navigation.ApplePieNavigator
import com.yurihondo.screentransitionsample.applepie.navigation.navigateToApplePieMr1Graph
import com.yurihondo.screentransitionsample.bananabread.navigation.BananaBreadNavigator
import com.yurihondo.screentransitionsample.bananabread.navigation.navigateToBananaBreadMr1Graph
import com.yurihondo.screentransitionsample.cupcake.navigation.CupcakeNavigator

internal class MainNavigator(
    private val navController: NavController,
): ApplePieNavigator, BananaBreadNavigator, CupcakeNavigator, DeepLinksNavigator {

    override fun navigateToBananaBreadMr1Graph() {
        navController.navigateToBananaBreadMr1Graph()
    }

    override fun navigateToApplePieMr1Graph(from: String) {
        navController.navigateToApplePieMr1Graph(from)
    }

    override fun navigateToBananaBreadMr1GraphFromExternal(clearStack: Boolean) {
        navController.navigateToBananaBreadMr1Graph(
            navOptions {
                if (clearStack) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
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
