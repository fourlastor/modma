package io.github.fourlastor.modma.app

enum class Screens(
    val screenName: String,
    val screenIcon: String,
    val screen: Screen,
) {
    MODS("Mods", "ic_mods.xml", Screen.Mods),
    DOWNLOAD("Download mods", "ic_download.xml", Screen.Download),
    SETTINGS("Settings", "ic_settings.xml", Screen.Settings),
}
