package io.github.fourlastor.modma.state

import kotlinx.coroutines.flow.StateFlow

interface ViewModel<State> {

    fun start()

    fun stop()

    val state: StateFlow<State>
}
