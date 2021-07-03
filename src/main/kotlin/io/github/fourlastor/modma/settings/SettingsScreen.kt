package io.github.fourlastor.modma.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.fourlastor.modma.state.Manager

@Composable
fun SettingsScreen(manager: Manager<SettingsState>) {
    DisposableEffect(true) {
        val job = manager.start()
        onDispose { job.cancel() }
    }

    val state = manager.state.collectAsState()

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        ModsFolder(
            name = "Enabled Mods",
            folder = state.value.enabledModsFolder,
            onFolderChanged = { manager.update(UpdateEnabledMods(it)) }
        )
        ModsFolder(
            name = "Disabled Mods",
            folder = state.value.disabledModsFolder,
            onFolderChanged = { manager.update(UpdateDisabledMods(it)) }
        )
    }
}

data class SettingsState(
    val enabledModsFolder: String?,
    val disabledModsFolder: String?,
)
