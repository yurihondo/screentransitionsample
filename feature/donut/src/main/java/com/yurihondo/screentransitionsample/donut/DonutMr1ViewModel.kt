package com.yurihondo.screentransitionsample.donut

import androidx.lifecycle.ViewModel
import com.yurihondo.screentransitionsample.donut.navigation.DonutMr1

class DonutMr1ViewModel(
    key: DonutMr1,
) : ViewModel() {
    val result: String = key.result
}
