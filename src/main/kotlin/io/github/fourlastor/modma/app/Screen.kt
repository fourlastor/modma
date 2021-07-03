package io.github.fourlastor.modma.app

import com.arkivanov.decompose.statekeeper.Parcelable

sealed class Screen: Parcelable {
    object Mods: Screen()
    object Settings: Screen()
    object Download: Screen()
}
