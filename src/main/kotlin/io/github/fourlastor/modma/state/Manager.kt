package io.github.fourlastor.modma.state

import kotlinx.coroutines.flow.*
import javax.inject.Inject

class Manager<State> @Inject constructor(
    initialState: State,
) {
    private val actions = MutableSharedFlow<Action<State>>()
    private val _state = MutableStateFlow(initialState)

    val state: StateFlow<State> = _state

    suspend fun start() = actions.collect { _state.value = it(_state.value) }

    suspend fun update(action: Action<State>) = actions.emit(action)

    suspend fun update(actions: Flow<Action<State>>) = actions.collect { update(it) }
}

interface Action<State> {
    operator fun invoke(state: State): State
}
