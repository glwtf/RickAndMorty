package ru.glwtf.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.glwtf.rickandmorty.data.paging.CharactersPagingSource
import ru.glwtf.rickandmorty.domain.entity.Character
import ru.glwtf.rickandmorty.domain.repository.RickMortyRepository
import javax.inject.Inject

class RickMortyRepositoryImpl @Inject constructor(
    private val pagingSource: CharactersPagingSource
): RickMortyRepository{
    override suspend fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    companion object {
        private const val MAX_PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = 2
    }
}