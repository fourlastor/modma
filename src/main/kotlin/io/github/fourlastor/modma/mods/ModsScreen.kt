package io.github.fourlastor.modma.mods

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.pop
import com.arkivanov.decompose.push
import io.github.fourlastor.modma.routing.rememberRouter

@Composable
fun ModsScreen() {
    val router =
        rememberRouter<Screen>(
            initialConfiguration = { Screen.List }
        )

    Children(
        routerState = router.state
    ) { screen ->
        when (val configuration = screen.configuration) {
            is Screen.List -> ModListScreen(onItemSelect = { router.push(Screen.Details(id = it)) })
            is Screen.Details -> ModDetailsScreen(item = configuration.id, onBack = router::pop)
        }
    }
}
