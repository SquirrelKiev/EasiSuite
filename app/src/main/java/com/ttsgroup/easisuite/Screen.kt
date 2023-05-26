package com.ttsgroup.easisuite

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    companion object {
        private val items = listOf(Home, AudioPlayer)
        val defaultScreen = Home

        fun getScreenByRoute(route: String): Screen? {
            return items.find { screen: Screen -> screen.route == route }
        }
    }

    object Home : Screen("home", R.string.home)
    object AudioPlayer : Screen("audio player", R.string.audio_player)
}
