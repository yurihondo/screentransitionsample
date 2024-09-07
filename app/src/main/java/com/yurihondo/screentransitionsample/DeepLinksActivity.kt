package com.yurihondo.screentransitionsample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class DeepLinksActivity : ComponentActivity() {

    companion object {
        private const val CUSTOM_SCHEME = "com.yurihondo"

        // authority
        private const val AUTHORITY_SHOW_BANANA_BREAD_MR1 = "show=banana_bread_mr1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)
        finish()
    }

    private fun handleIntent(intent: Intent) {
        val uri = intent.data ?: return
        when (uri.scheme) {
            CUSTOM_SCHEME -> {
                when (uri.authority) {
                    AUTHORITY_SHOW_BANANA_BREAD_MR1 -> {
                        startActivity(MainActivity.createIntentToShowBananaBreadMr1(this@DeepLinksActivity))
                    }
                }
            }
        }
    }
}
