package com.yurihondo.applepie.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// region Graph
@Serializable
object ApplePieGraph

@Serializable
data class ApplePieMr1Graph (
    val from: String = "unknown",
)
// endregion Graph

// region Destination
@Serializable
object ApplePieDestination

@Serializable
data class ApplePieMr1Destination(
    @SerialName("from")
    val from: String = "unknown",
)
// endregion Destination
