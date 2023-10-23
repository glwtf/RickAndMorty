package ru.glwtf.rickandmorty.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.glwtf.rickandmorty.data.mapper.Mapper.mapToDomainCharacters
import ru.glwtf.rickandmorty.data.network.ApiClient
import ru.glwtf.rickandmorty.domain.entity.Character
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(
    private val rickMortyApi: ApiClient
) : PagingSource<Int,Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val pageNumber = params.key ?: 1
            val response = rickMortyApi.getCharacters(pageNumber)
            LoadResult.Page(
                response.mapToDomainCharacters(),
                prevKey = params.prevKey(),
                nextKey = params.nextKey(response.info.totalPagesCount)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun LoadParams<Int>.prevKey() : Int? {
        val curKey = key
        return if (curKey == null) null
        else if (curKey - 1 < 1) null
        else curKey - 1
    }

    private fun LoadParams<Int>.nextKey(totalSize: Int) : Int? {
        val curKey = key
        return if (curKey == null) null
        else if (curKey + 1 > totalSize) null
        else curKey + 1
    }
}