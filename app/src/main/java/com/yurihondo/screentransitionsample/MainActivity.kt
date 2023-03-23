package com.yurihondo.screentransitionsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yurihondo.screentransitionsample.ui.theme.ScreenTransitionSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenTransitionSampleTheme {
                App()
            }
        }
    }
}
