package ru.glwtf.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import ru.glwtf.rickandmorty.domain.entity.Character

@Composable
fun NavGraph(
    hostController: NavHostController,
    charactersScreenContent: @Composable () -> Unit,
    characterScreenContent: @Composable (Character) -> Unit,
) {
    NavHost(
        navController = hostController,
        startDestination = Screens.HomeScreen.route
    ) {
        navigation(
            startDestination = Screens.CharactersScreen.route,
            route = Screens.HomeScreen.route
        ) {
            composable(Screens.CharactersScreen.route) {
                charactersScreenContent()
            }
            composable(
                route = Screens.CharacterScreen.route,
                arguments = listOf(
                    navArgument(Screens.KEY_CHARACTER) { type = NavType.StringType }
                )
            ) {
                val characterJson = it.arguments?.getString(Screens.KEY_CHARACTER) ?: ""
                val character = Gson().fromJson(characterJson, Character::class.java)
                characterScreenContent(character)
            }
        }
    }
}