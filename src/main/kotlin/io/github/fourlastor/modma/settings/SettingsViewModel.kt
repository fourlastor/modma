package io.github.fourlastor.modma.settings

import io.github.fourlastor.modma.state.Manager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository,
    private val manager: Manager<SettingsState>,
) {
    private val scope = CoroutineScope(Dispatchers.Default)

    fun start() = scope.launch {
        val state = repository.read()
        val managerAsync = async { manager.start() }
        if (state != null) {
            manager.update(ReplaceState(state))
        }

        awaitAll(
            managerAsync,
            async { manager.state.drop(1).collect { repository.save(it) } }
        )
    }

    val state = manager.state

    fun saveEnabledMods(path: String) =
        scope.launch {
            manager.update(UpdateEnabledMods(path))
        }

    fun saveDisabledMods(path: String) =
        scope.launch {
            manager.update(UpdateDisabledMods(path))
        }
}

