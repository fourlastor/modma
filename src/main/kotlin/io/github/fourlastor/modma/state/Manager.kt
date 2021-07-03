package io.github.fourlastor.modma.state

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class Manager<State> @Inject constructor(
    initialState: State,
) {
    private val actions = MutableSharedFlow<Action<State>>()
    private val _state = MutableStateFlow(initialState)
    private val scope = CoroutineScope(Dispatchers.Default)

    val state: StateFlow<State> = _state

    fun start() = scope.launch {
        actions.collect { _state.value = it(_state.value) }
    }


    fun update(action: Action<State>) {
        scope.launch {
            actions.emit(action)
        }
    }

    fun update(actions: Flow<Action<State>>) {
        scope.launch {
            actions.collect { update(it) }
        }
    }
}

interface Action<State> {
    operator fun invoke(state: State): State
}
