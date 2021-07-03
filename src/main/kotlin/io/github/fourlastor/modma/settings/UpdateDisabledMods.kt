package io.github.fourlastor.modma.settings

import io.github.fourlastor.modma.state.Action

class UpdateDisabledMods(
    private val path: String
) : Action<SettingsState> {
    override fun invoke(state: SettingsState) = state.copy(
        disabledModsFolder = path
    )
}
