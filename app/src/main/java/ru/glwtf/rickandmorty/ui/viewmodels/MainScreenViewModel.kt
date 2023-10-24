package ru.glwtf.rickandmorty.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.glwtf.rickandmorty.domain.entity.Character
import ru.glwtf.rickandmorty.domain.usecase.GetCharactersUseCase
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
): ViewModel() {
    private val _charactersState = MutableStateFlow<PagingData<Character>>(
        PagingData.empty()
    )
    val charactersState: StateFlow<PagingData<Character>>
        get() = _charactersState

    init {
        viewModelScope.launch {
            getCharacters()
        }
    }

    private suspend fun getCharacters() {
        getCharactersUseCase().collect{
            _charactersState.value = it
        }
    }
}