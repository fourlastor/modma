package io.github.fourlastor.modma.settings

import io.github.fourlastor.modma.app.Dirs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    private val dirs: Dirs,
) {

    suspend fun read(): SettingsState? = withContext(Dispatchers.IO) {
        val file = configFile()
        if (file.exists()) {
            Json.decodeFromString<SettingsState>(file.readText())
        } else {
            null
        }
    }

    suspend fun save(state: SettingsState) = withContext(Dispatchers.IO) {
        val configFile = configFile()
        if (!configFile.exists()) {
            configFile.parentFile.mkdirs()
        }
        configFile
            .writeText(Json.encodeToString(state))
    }

    private fun configFile(): File {
        println("${dirs.config}/config.json")
        return File(dirs.config, "config.json")
    }
}


