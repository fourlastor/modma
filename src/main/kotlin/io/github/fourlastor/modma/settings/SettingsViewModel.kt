package io.github.fourlastor.modma.settings

import io.github.fourlastor.modma.state.Manager
import io.github.fourlastor.modma.state.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository,
    private val manager: Manager<SettingsState>,
    dispatchers: Dispatchers,
) : ViewModel<SettingsState> {
    private val scope = CoroutineScope(dispatchers.Default)

    override fun start() {
        scope.launch {
            val state = repository.read()
            if (state != null) {
                manager.update(ReplaceState(state))
            }

            manager.state.drop(1).collect { repository.save(it) }
        }
    }

    override fun stop() {
        scope.cancel()
    }

    override val state = manager.state

    fun saveEnabledMods(path: String) {
        manager.update(UpdateEnabledMods(path))
    }

    fun saveDisabledMods(path: String) {
        manager.update(UpdateDisabledMods(path))
    }
}

