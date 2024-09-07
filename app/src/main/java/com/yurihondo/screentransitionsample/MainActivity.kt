package com.yurihondo.screentransitionsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yurihondo.screentransitionsample.navigation.DeepLinksNavigator
import com.yurihondo.screentransitionsample.ui.App
import com.yurihondo.screentransitionsample.ui.theme.ScreenTransitionSampleTheme

class MainActivity : ComponentActivity() {

    companion object {
        fun createIntentToShowBananaBreadMr1(activityContext: Context): Intent {
            return Intent(activityContext, MainActivity::class.java).apply {
                action = DeepLinksNavigator.ACTION_SHOW_BANANA_BREAD_MR1
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
