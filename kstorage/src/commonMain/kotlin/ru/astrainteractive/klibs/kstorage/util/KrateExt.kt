package ru.astrainteractive.klibs.kstorage.util

import ru.astrainteractive.klibs.kstorage.api.MutableKrate
import ru.astrainteractive.klibs.kstorage.suspend.FlowMutableKrate
import ru.astrainteractive.klibs.kstorage.suspend.SuspendMutableKrate

object KrateExt {
    /**
     * This will call [MutableKrate.reset] and return [MutableKrate.cachedValue]
     */
    fun <T> MutableKrate<T>.resetAndGet(): T {
        reset()
        return cachedValue
    }

    /**
     * Save value with a reference to current
     */
    fun <T> MutableKrate<T>.update(block: (T) -> T) {
        save(block.invoke(cachedValue))
    }

    /**
     * Save value with a reference to current and return new value
     */
    fun <T> MutableKrate<T>.updateAndGet(block: (T) -> T): T {
        val oldValue = loadAndGet()
        val newValue = block.invoke(oldValue)
        save(newValue)
        return newValue
    }

    /**
     * This will call [FlowMutableKrate.reset] and return first [FlowMutableKrate.flow]
     */
    suspend fun <T> SuspendMutableKrate<T>.resetAndGet(): T {
        reset()
        return loadAndGet()
    }

    /**
     * Save value with a reference to current
     */
    suspend fun <T> SuspendMutableKrate<T>.update(block: suspend (T) -> T) {
        val oldValue = loadAndGet()
        val newValue = block.invoke(oldValue)
        save(newValue)
    }

    /**
     * Save value with a reference to current and return new value
     */
    suspend fun <T> SuspendMutableKrate<T>.updateAndGet(block: suspend (T) -> T): T {
        val oldValue = loadAndGet()
        val newValue = block.invoke(oldValue)
        save(newValue)
        return newValue
    }
}
