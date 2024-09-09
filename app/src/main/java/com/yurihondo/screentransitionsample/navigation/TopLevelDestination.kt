package com.yurihondo.screentransitionsample.navigation

import com.ramcosta.composedestinations.spec.DirectionNavGraphSpec
import com.yurihondo.screentransitionsample.R
import com.yurihondo.screentransitionsample.applepie.navgraphs.ApplePieNavGraph
import com.yurihondo.screentransitionsample.bananabread.navgraphs.BananaBreadNavGraph
import com.yurihondo.screentransitionsample.cupcake.navgraphs.CupcakeNavGraph
import com.yurihondo.screentransitionsample.donut.navgraphs.DonutNavGraph
import com.yurihondo.screentransitionsample.eclair.navgraphs.EclairNavGraph

internal enum class TopLevelDestination(
    val titleTextId: Int,
    val graph: () -> DirectionNavGraphSpec
) {
    UNKNOWN(
        titleTextId = -1,
        graph = { throw UnsupportedOperationException("Unknown destination") },
    ),
    APPLE_PIE(
        titleTextId = R.string.destination_name_apple_pie,
        graph = { ApplePieNavGraph },
    ),

    BANANA_BREAD(
        titleTextId = R.string.destination_name_banana_bread,
        graph = { BananaBreadNavGraph },
    ),
    CUPCAKE(
        titleTextId = R.string.destination_name_cupcake,
        graph = { CupcakeNavGraph },
    ),
    DONUT(
        titleTextId = R.string.destination_name_donut,
        graph = { DonutNavGraph },
    ),
    ECLAIR(
        titleTextId = R.string.destination_name_eclair,
        graph = { EclairNavGraph },
    );

    companion object {
        fun getAvailableDestinations(): List<TopLevelDestination> {
            return TopLevelDestination.entries.filter { it != UNKNOWN }
        }
    }
}
