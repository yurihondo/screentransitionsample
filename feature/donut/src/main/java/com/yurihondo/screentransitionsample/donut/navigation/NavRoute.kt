package com.yurihondo.screentransitionsample.donut.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Donut : NavKey

@Serializable
data class DonutMr1(
    val result: String,
) : NavKey
