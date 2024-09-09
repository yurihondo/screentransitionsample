package com.yurihondo.screentransitionsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yurihondo.screentransitionsample.core.ui.App
import com.yurihondo.screentransitionsample.core.ui.theme.ScreenTransitionSampleTheme
import com.yurihondo.screentransitionsample.navigation.DeepLinksNavigator

class MainActivity : ComponentActivity() {

    companion object {
        fun createIntentToShowBananaBreadMr1(activityContext: Context, clearStack: Boolean): Intent {
            return Intent(activityContext, MainActivity::class.java).apply {
                action = DeepLinksNavigator.ACTION_SHOW_BANANA_BREAD_MR1
                putExtra(DeepLinksNavigator.KEY_CLEAR_STACK, clearStack)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenTransitionSampleTheme {
                App()
            }
        }
    }
}
