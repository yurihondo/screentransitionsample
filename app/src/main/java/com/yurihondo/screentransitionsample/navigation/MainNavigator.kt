package com.yurihondo.screentransitionsample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.utils.navGraph
import com.ramcosta.composedestinations.utils.startDestination
import com.ramcosta.composedestinations.utils.toDestinationsNavigator
import com.yurihondo.screentransitionsample.applepie.destinations.ApplePieMr1RouteDestination
import com.yurihondo.screentransitionsample.applepie.navigation.ApplePieNavigator
import com.yurihondo.screentransitionsample.bananabread.destinations.BananaBreadMr1RouteDestination
import com.yurihondo.screentransitionsample.bananabread.navigation.BananaBreadNavigator
import com.yurihondo.screentransitionsample.cupcake.navigation.CupcakeNavigator

internal class MainNavigator(
    private val navController: NavController,
    private val destinationsNavigator: DestinationsNavigator = navController.toDestinationsNavigator()
) : ApplePieNavigator, BananaBreadNavigator, CupcakeNavigator, DeepLinksNavigator {

    override fun navigateToBananaBreadMr1Graph() {
        destinationsNavigator.navigate(BananaBreadMr1RouteDestination)
    }

    override fun navigateToApplePieMr1Graph(from: String) {
        destinationsNavigator.navigate(ApplePieMr1RouteDestination(from))
    }

    override fun navigateToBananaBreadMr1GraphFromExternal(clearStack: Boolean) {
        destinationsNavigator.navigate(BananaBreadMr1RouteDestination) {
            if (clearStack) {
                popUpTo(navController.navGraph.startDestination) {
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
