package com.yurihondo.applepie.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data object ApplePie : NavKey

@Serializable
data class ApplePieMr1(
    @SerialName("from")
    val from: String = "unknown",
) : NavKey

@Serializable
data class Edit(
    val from: String = "unknown",
) : NavKey {
    companion object {
        const val RESULT_KEY_FROM = "result_key_from"
    }
}
