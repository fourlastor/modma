package io.github.fourlastor.modma.app

import dagger.Module
import dagger.Provides
import net.harawata.appdirs.AppDirs
import net.harawata.appdirs.AppDirsFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideAppDirs(): AppDirs = AppDirsFactory.getInstance()
}
