package io.github.fourlastor.modma.settings

import io.github.fourlastor.modma.state.Action

class ReplaceState(
    private val newState: SettingsState
) : Action<SettingsState> {
    override fun invoke(state: SettingsState): SettingsState = newState
}
