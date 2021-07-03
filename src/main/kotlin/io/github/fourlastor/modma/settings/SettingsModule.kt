package io.github.fourlastor.modma.settings

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.fourlastor.modma.state.Manager
import javax.inject.Singleton

@Module
class SettingsModule {

    @Provides
    @Singleton
    fun bindSettingsManager(): Manager<SettingsState> = Manager(
        SettingsState(null, null)
    )
}
