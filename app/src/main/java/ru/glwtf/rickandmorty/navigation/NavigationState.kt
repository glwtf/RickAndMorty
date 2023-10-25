package ru.glwtf.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.glwtf.rickandmorty.domain.entity.Character

class NavigationState (val navHostController: NavHostController) {
    fun navigateToCharacter(character: Character) {
        navHostController.navigate(Screens.CharacterScreen.getRouteWithArgs(character))
    }
}

@Composable
fun rememberNavigationState(): NavigationState {
    val navHostController = rememberNavController()
    return remember {
        NavigationState(navHostController)
    }
}