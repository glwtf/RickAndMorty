package ru.glwtf.rickandmorty.data.mapper

import ru.glwtf.rickandmorty.data.model.CharactersDto
import ru.glwtf.rickandmorty.domain.entity.Character

object Mapper {
    fun CharactersDto.mapToDomainCharacters(): List<Character> {
        val result = mutableListOf<Character>()
        characterList.forEach { characterDto ->
            val character = Character(
                characterId = characterDto.characterId,
                characterName = characterDto.characterName,
                characterLiveStatus = characterDto.characterLiveStatus,
                characterSpecies = characterDto.characterSpecies,
                characterSubspecies = characterDto.characterSubspecies,
                characterSex = characterDto.characterSex,
                characterBirthPlace = characterDto.characterBirthPlace.placeName,
                lastLocation = characterDto.lastLocation.placeName,
                characterAvatar = characterDto.characterAvatar,
            )
            result.add(character)
        }
        return result
    }
}