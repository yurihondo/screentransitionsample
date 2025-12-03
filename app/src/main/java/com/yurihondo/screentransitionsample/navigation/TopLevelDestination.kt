package com.yurihondo.screentransitionsample.navigation

import androidx.navigation3.runtime.NavKey
import com.yurihondo.applepie.navigation.ApplePie
import com.yurihondo.bananabread.navigation.BananaBread
import com.yurihondo.cupcake.navigation.Cupcake
import com.yurihondo.eclair.navigation.Eclair
import com.yurihondo.screentransitionsample.R
import com.yurihondo.screentransitionsample.donut.navigation.Donut

internal enum class TopLevelDestination(
    val titleTextId: Int,
    val startRoute: NavKey,
) {
    UNKNOWN(
        titleTextId = -1,
        startRoute = ApplePie, // Fallback, should never be used
    ),
    APPLE_PIE(
        titleTextId = R.string.destination_name_apple_pie,
        startRoute = ApplePie,
    ),
    BANANA_BREAD(
        titleTextId = R.string.destination_name_banana_bread,
        startRoute = BananaBread,
    ),
    CUPCAKE(
        titleTextId = R.string.destination_name_cupcake,
        startRoute = Cupcake,
    ),
    DONUT(
        titleTextId = R.string.destination_name_donut,
        startRoute = Donut,
    ),
    ECLAIR(
        titleTextId = R.string.destination_name_eclair,
        startRoute = Eclair,
    );

    companion object {
        fun getAvailableDestinations(): List<TopLevelDestination> {
            return TopLevelDestination.entries.filter { it != UNKNOWN }
        }

        fun fromNavKey(navKey: NavKey): TopLevelDestination {
            return entries.find { it.startRoute == navKey } ?: UNKNOWN
        }
    }
}
