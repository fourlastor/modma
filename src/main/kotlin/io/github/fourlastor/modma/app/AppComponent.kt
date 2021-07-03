package io.github.fourlastor.modma.app

import dagger.Component
import io.github.fourlastor.modma.settings.SettingsModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [SettingsModule::class]
)
interface AppComponent {
    fun app(): App
}
