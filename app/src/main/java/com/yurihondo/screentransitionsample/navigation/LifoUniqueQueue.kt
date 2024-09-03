package com.yurihondo.screentransitionsample.navigation

import java.util.concurrent.CopyOnWriteArraySet

internal class LifoUniqueQueue<T>(
    defaultDestinations: Set<T> = emptySet()
) {
    private val items = CopyOnWriteArraySet(defaultDestinations)

    fun add(item: T): Boolean {
        if (items.contains(item)) {
            items.remove(item)
        }
        return items.add(item)
    }

    fun remove(): T? {
        return if (items.isEmpty()) {
            null
        } else {
            items.last().also { items.remove(it) }
        }
    }

    fun element(): T? {
        return if (items.isEmpty()) {
            null
        } else {
            items.last()
        }
    }

    fun toSet(): Set<T> {
        return items
    }

    override fun toString(): String {
        return items.toString()
    }
}
