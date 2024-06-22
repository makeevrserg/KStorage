package ru.astrainteractive.klibs.kstorage.api

import kotlinx.coroutines.flow.StateFlow

interface StateFlowKrate<T> : Krate<T>, CachedKrate.Coroutine<T> {
    override val cachedStateFlow: StateFlow<T>

    override val cachedValue: T
        get() = cachedStateFlow.value
}
