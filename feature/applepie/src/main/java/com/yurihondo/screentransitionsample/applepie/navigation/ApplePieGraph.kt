package com.yurihondo.screentransitionsample.applepie.navigation

import com.ramcosta.composedestinations.annotation.ExternalModuleGraph
import com.ramcosta.composedestinations.annotation.NavGraph

@NavGraph<ExternalModuleGraph>
internal annotation class ApplePieGraph

@NavGraph<ApplePieGraph>
internal annotation class ApplePieMr1Graph
