package io.github.fourlastor.modma.settings

import kotlinx.serialization.Serializable

@Serializable
data class SettingsState(
    val enabledModsFolder: String?,
    val disabledModsFolder: String?,
)
