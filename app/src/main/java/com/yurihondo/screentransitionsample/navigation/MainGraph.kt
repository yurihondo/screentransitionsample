package com.yurihondo.screentransitionsample.navigation

import com.ramcosta.composedestinations.annotation.ExternalNavGraph
import com.ramcosta.composedestinations.annotation.NavHostGraph
import com.yurihondo.screentransitionsample.applepie.navgraphs.ApplePieNavGraph
import com.yurihondo.screentransitionsample.bananabread.navgraphs.BananaBreadNavGraph
import com.yurihondo.screentransitionsample.cupcake.navgraphs.CupcakeNavGraph
import com.yurihondo.screentransitionsample.donut.navgraphs.DonutNavGraph
import com.yurihondo.screentransitionsample.eclair.navgraphs.EclairNavGraph

@NavHostGraph
annotation class MainGraph {

    @ExternalNavGraph<ApplePieNavGraph>(start = true)
    @ExternalNavGraph<BananaBreadNavGraph>
    @ExternalNavGraph<CupcakeNavGraph>
    @ExternalNavGraph<DonutNavGraph>
    @ExternalNavGraph<EclairNavGraph>
    companion object Include
}
