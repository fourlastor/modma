package io.github.fourlastor.modma.mods

import com.arkivanov.decompose.statekeeper.Parcelable

sealed class Screen: Parcelable {
    object List : Screen()
    data class Details(val id: Int) : Screen()
}
