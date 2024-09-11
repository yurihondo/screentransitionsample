package com.yurihondo.screentransitionsample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.yurihondo.applepie.navigation.ApplePieMr1Destination
import com.yurihondo.bananabread.navigation.BananaBreadMr1Destination
import com.yurihondo.screentransitionsample.applepie.navigation.ApplePieNavigator
import com.yurihondo.screentransitionsample.bananabread.navigation.BananaBreadNavigator
import com.yurihondo.screentransitionsample.cupcake.navigation.CupcakeNavigator

internal class MainNavigator(
    private val navController: NavController,
) : ApplePieNavigator, BananaBreadNavigator, CupcakeNavigator, DeepLinksNavigator {

    override fun navigateToBananaBreadMr1() {
        navController.navigate(BananaBreadMr1Destination)
    }

    override fun navigateToApplePieMr1(from: String) {
        navController.navigate(ApplePieMr1Destination(from))
    }

    override fun navigateToBananaBreadMr1FromExternal(clearStack: Boolean) {
        navController.navigate(BananaBreadMr1Destination) {
            if (clearStack) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
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
