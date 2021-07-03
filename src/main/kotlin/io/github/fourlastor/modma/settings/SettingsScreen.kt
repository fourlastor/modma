package io.github.fourlastor.modma.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    DisposableEffect(true) {
        val job = viewModel.start()
        onDispose { job.cancel() }
    }

    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        ModsFolder(
            name = "Enabled Mods",
            folder = state.value.enabledModsFolder,
            onFolderChanged = { viewModel.saveEnabledMods(it) }
        )
        ModsFolder(
            name = "Disabled Mods",
            folder = state.value.disabledModsFolder,
            onFolderChanged = { viewModel.saveDisabledMods(it) }
        )
    }
}

