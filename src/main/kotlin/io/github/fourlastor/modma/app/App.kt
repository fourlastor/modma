package io.github.fourlastor.modma.app

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.replaceCurrent
import io.github.fourlastor.modma.download.DownloadScreen
import io.github.fourlastor.modma.mods.ModsScreen
import io.github.fourlastor.modma.routing.rememberRouter
import io.github.fourlastor.modma.settings.SettingsScreen
import io.github.fourlastor.modma.support.exhaustive
import javax.inject.Inject

class App @Inject constructor() {
    fun start() {
        Window(
            title = "ModMa",
        ) {
            MaterialTheme {
                val router =
                    rememberRouter<Screen>(
                        initialConfiguration = { Screen.Mods }
                    )

                Column {
                    Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                        ScreenList(
                            onRoute = { router.replaceCurrent(it) },
                            modifier = Modifier.padding(4.dp)
                                .width(150.dp)
                                .fillMaxHeight()
                        )
                        Children(
                            routerState = router.state
                        ) { screen ->
                            when (screen.configuration) {
                                is Screen.Mods -> ModsScreen()
                                is Screen.Download -> DownloadScreen()
                                is Screen.Settings -> SettingsScreen()
                            }.exhaustive()
                        }
                    }
                }
            }
        }
    }
}