package ru.glwtf.rickandmorty.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.glwtf.rickandmorty.domain.entity.Character

interface RickMortyRepository {
    suspend fun getCharacters(): Flow<PagingData<Character>>
}