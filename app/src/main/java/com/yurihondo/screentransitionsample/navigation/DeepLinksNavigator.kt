package com.yurihondo.screentransitionsample.navigation

import android.content.Intent

internal interface DeepLinksNavigator {

    companion object {
        // actions
        const val ACTION_SHOW_BANANA_BREAD_MR1 = "com.yurihondo.screentransitionsample.action.SHOW_BANANA_BREAD_MR1"
    }

    fun handleDeepLinksIfNeeded(intent: Intent): Boolean {
        var consumed = true
        when (intent.action) {
            ACTION_SHOW_BANANA_BREAD_MR1 -> {
                navigateToBananaBreadMr1Graph()
            }
            else -> {
                consumed = false
            }
        }
        return consumed
    }

    fun navigateToBananaBreadMr1Graph()
}
