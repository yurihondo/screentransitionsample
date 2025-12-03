package com.yurihondo.screentransitionsample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * CompositionLocal for accessing ResultStore.
 */
val LocalResultStore = staticCompositionLocalOf<ResultStore> {
    error("No ResultStore provided")
}

/**
 * Remember and save a ResultStore across configuration changes.
 */
@Composable
fun rememberResultStore(): ResultStore {
    return rememberSaveable(saver = ResultStoreSaver) {
        ResultStore()
    }
}

/**
 * A store for passing results between screens.
 * Based on nav3-recipes state-based result pattern.
 */
class ResultStore(
    @PublishedApi
    internal val resultStateMap: MutableMap<String, MutableState<Any?>> = mutableMapOf()
) {
    /**
     * Get result state for consuming.
     * Returns a State that can be observed for changes.
     */
    inline fun <reified T> getResultState(key: String = T::class.java.name): State<T?> {
        @Suppress("UNCHECKED_CAST")
        return resultStateMap.getOrPut(key) { mutableStateOf(null) } as State<T?>
    }

    /**
     * Set a result to be consumed by another screen.
     */
    fun setResult(result: Any?, key: String) {
        val state = resultStateMap.getOrPut(key) { mutableStateOf(null) }
        state.value = result
    }

    /**
     * Remove a result after consumption.
     */
    fun removeResult(key: String) {
        resultStateMap[key]?.value = null
    }

    /**
     * Clear a result by type.
     */
    inline fun <reified T> clearResult(key: String = T::class.java.name) {
        removeResult(key)
    }

    internal fun toMap(): Map<String, Any?> {
        return resultStateMap.mapValues { it.value.value }
    }
}

/**
 * Saver for ResultStore to survive process death.
 */
private val ResultStoreSaver = Saver<ResultStore, Map<String, Any?>>(
    save = { store -> store.toMap() },
    restore = { map ->
        ResultStore(
            map.mapValues { mutableStateOf(it.value) }.toMutableMap()
        )
    }
)
