package ru.glwtf.rickandmorty.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersDto (
    @SerialName("info") val info: InfoDto,
    @SerialName("results") val characterList: List<CharacterDto>
) {
    @Serializable
    data class InfoDto(
        @SerialName("next") val nextPage: String?,
        @SerialName("prev") val prevPage: String?,
    )

    @Serializable
    data class CharacterDto(
        @SerialName("id") val characterId: Int,
        @SerialName("name") val characterName: String,
        @SerialName("status") val characterLiveStatus: String,
        @SerialName("species") val characterSpecies: String,
        @SerialName("type") val characterSubspecies: String,
        @SerialName("gender") val characterSex: String,
        @SerialName("origin") val characterBirthPlace: OriginOrLocationDto,
        @SerialName("location") val lastLocation: OriginOrLocationDto,
        @SerialName("image") val characterAvatar: String,
    )

    @Serializable
    data class OriginOrLocationDto(
        @SerialName("name") val placeName: String,
        @SerialName("url") val placeUrl: String,
    )
}