package ru.glwtf.rickandmorty.domain.usecase

import ru.glwtf.rickandmorty.domain.repository.RickMortyRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repo: RickMortyRepository) {
    suspend operator fun invoke() = repo.getCharacters()
}