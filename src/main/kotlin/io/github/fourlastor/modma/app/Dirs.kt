package io.github.fourlastor.modma.app

import net.harawata.appdirs.AppDirs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Dirs @Inject constructor(
    private val appDirs: AppDirs
) {
    val config: String
        get() = appDirs.getUserConfigDir("modma", "1.0.0", "fourlastor")
}
