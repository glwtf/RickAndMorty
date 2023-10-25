package ru.glwtf.rickandmorty.navigation

import android.net.Uri
import com.google.gson.Gson
import ru.glwtf.rickandmorty.domain.entity.Character

sealed class Screens (val route: String) {
    object HomeScreen: Screens(ROUTE_HOME) //вложенный граф, котором находят наши два экрана
    object CharactersScreen: Screens(ROUTE_CHARACTERS)
    object CharacterScreen: Screens(ROUTE_CHARACTER) {
        private const val ROUTE_BEFORE_ARGS = "character"
        fun getRouteWithArgs(character: Character): String {
            val characterJson = Gson().toJson(character)
            return "$ROUTE_BEFORE_ARGS/${characterJson.encode()}"
        }
    }

    internal fun String.encode() = Uri.encode(this)

    companion object {
        const val KEY_CHARACTER = "character_arg"
        private const val ROUTE_HOME = "home"
        private const val ROUTE_CHARACTERS = "characters"
        private const val ROUTE_CHARACTER = "character/{$KEY_CHARACTER}"
    }
}