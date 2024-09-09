package com.yurihondo.screentransitionsample.bananabread.navigation

import com.ramcosta.composedestinations.annotation.ExternalModuleGraph
import com.ramcosta.composedestinations.annotation.NavGraph

@NavGraph<ExternalModuleGraph>
internal annotation class BananaBreadGraph

@NavGraph<BananaBreadGraph>
internal  annotation class BananaBreadMr1Graph
