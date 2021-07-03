package io.github.fourlastor.modma

import io.github.fourlastor.modma.app.DaggerAppComponent

fun main() {
    val app = DaggerAppComponent
        .create()
        .app()
    app.start()
}

