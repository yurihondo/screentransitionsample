package com.yurihondo.screentransitionsample.navigation

import com.yurihondo.applepie.navigation.AppleGraphSpec
import com.yurihondo.bananabread.navigation.BananaBreadGraphSpec
import com.yurihondo.core.ui.navigation.GraphSpec
import com.yurihondo.cupcake.navigation.CupcakeGraphSpec
import com.yurihondo.donut.navigation.DonutGraphSpec
import com.yurihondo.eclair.navigation.EclairGraphSpec
import com.yurihondo.screentransitionsample.R

enum class TopLevelDestination(
    val titleTextId: Int,
    val graph: GraphSpec
) {
    APPLE_PIE(
        titleTextId = R.string.destination_name_apple_pie,
        graph = AppleGraphSpec(),
    ),

    BANANA_BREAD(
        titleTextId = R.string.destination_name_banana_bread,
        graph = BananaBreadGraphSpec(),
    ),
    CUPCAKE(
        titleTextId = R.string.destination_name_cupcake,
        graph = CupcakeGraphSpec()
    ),
    DONUT(
        titleTextId = R.string.destination_name_donut,
        graph = DonutGraphSpec()
    ),
    ECLAIR(
        titleTextId = R.string.destination_name_eclair,
        graph = EclairGraphSpec(),
    );
}
