package io.github.fourlastor.modma.app

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = []
)
interface AppComponent {
    fun app(): App
}
