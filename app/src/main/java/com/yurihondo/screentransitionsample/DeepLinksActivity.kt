package com.yurihondo.screentransitionsample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class DeepLinksActivity : ComponentActivity() {

    companion object {
        private const val CUSTOM_URI_SCHEME = "com.yurihondo"

        // authority
        private const val AUTHORITY_SHOW_BANANA_BREAD_MR1 = "show=banana_bread_mr1"

        // params
        private const val EXIT_APP = "exit_app"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finish()
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        val uri = intent.data ?: return
        when (uri.scheme) {
            CUSTOM_URI_SCHEME -> {
                when (uri.authority) {
                    AUTHORITY_SHOW_BANANA_BREAD_MR1 -> {
                        val exitApp = uri.getBooleanQueryParameter(EXIT_APP, false)
                        startActivity(
                            MainActivity.createIntentToShowBananaBreadMr1(
                                activityContext = this@DeepLinksActivity,
                                clearStack = exitApp,
                            ).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        )
                    }
                }
            }
        }
    }
}
