package io.github.fourlastor.modma.state

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class Manager<State> @Inject constructor(
    initialState: State,
) {
    private val _state = MutableStateFlow(initialState)

    val state: StateFlow<State> = _state

    fun update(action: Action<State>) {
        _state.value = action(_state.value)
    }

    suspend fun update(actions: Flow<Action<State>>) = actions.collect { update(it) }
}

interface Action<State> {
    operator fun invoke(state: State): State
}
