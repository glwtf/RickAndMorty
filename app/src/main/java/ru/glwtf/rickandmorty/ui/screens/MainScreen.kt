package ru.glwtf.rickandmorty.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ru.glwtf.rickandmorty.ui.viewmodels.MainScreenViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import ru.glwtf.rickandmorty.navigation.NavGraph
import ru.glwtf.rickandmorty.navigation.rememberNavigationState

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val characterPagingItem = viewModel.charactersState.collectAsLazyPagingItems()
    val navigationState = rememberNavigationState()

    NavGraph(
        hostController = navigationState.navHostController,
        charactersScreenContent = {
            CharactersListScreen(
                characterPagingItem,
                onClickListener = { character ->
                    navigationState.navigateToCharacter(character)
                }
            )
        },
        characterScreenContent = {
            CharacterScreen(
                character = it,
                onBackPressedClickListener = { navigationState.navHostController.popBackStack() }
            )
        }
    )
}
