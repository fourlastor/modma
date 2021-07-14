package io.github.fourlastor.modma.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.fourlastor.modma.state.ViewModelContainer

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    ViewModelContainer(viewModel) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            ModsFolder(
                name = "Enabled Mods",
                folder = it.enabledModsFolder,
                onFolderChanged = { viewModel.saveEnabledMods(it) }
            )
            ModsFolder(
                name = "Disabled Mods",
                folder = it.disabledModsFolder,
                onFolderChanged = { viewModel.saveDisabledMods(it) }
            )
        }
    }
}

