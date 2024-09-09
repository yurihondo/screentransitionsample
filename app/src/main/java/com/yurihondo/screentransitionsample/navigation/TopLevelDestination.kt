package com.yurihondo.screentransitionsample.navigation

import com.yurihondo.screentransitionsample.R
import com.yurihondo.screentransitionsample.applepie.navigation.AppleGraphSpec
import com.yurihondo.screentransitionsample.bananabread.navigation.BananaBreadGraphSpec
import com.yurihondo.screentransitionsample.core.ui.navigation.GraphSpec
import com.yurihondo.screentransitionsample.cupcake.navigation.CupcakeGraphSpec
import com.yurihondo.screentransitionsample.donut.navigation.DonutGraphSpec
import com.yurihondo.screentransitionsample.eclair.navigation.EclairGraphSpec

internal enum class TopLevelDestination(
    val titleTextId: Int,
    val graph: () -> GraphSpec
) {
    UNKNOWN(
        titleTextId = -1,
        graph = { throw UnsupportedOperationException("Unknown destination") },
    ),
    APPLE_PIE(
        titleTextId = R.string.destination_name_apple_pie,
        graph = { AppleGraphSpec() },
    ),

    BANANA_BREAD(
        titleTextId = R.string.destination_name_banana_bread,
        graph = { BananaBreadGraphSpec() },
    ),
    CUPCAKE(
        titleTextId = R.string.destination_name_cupcake,
        graph = { CupcakeGraphSpec() },
    ),
    DONUT(
        titleTextId = R.string.destination_name_donut,
        graph = { DonutGraphSpec() },
    ),
    ECLAIR(
        titleTextId = R.string.destination_name_eclair,
        graph = { EclairGraphSpec() },
    );

    companion object {
        fun getAvailableDestinations(): List<TopLevelDestination> {
            return TopLevelDestination.entries.filter { it != UNKNOWN }
        }
    }
}
