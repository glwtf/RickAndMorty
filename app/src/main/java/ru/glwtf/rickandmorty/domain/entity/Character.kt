package ru.glwtf.rickandmorty.domain.entity

import androidx.compose.runtime.Immutable
import javax.inject.Inject

@Immutable
data class Character @Inject constructor(
    val characterId: Int,
    val characterName: String,
    val characterLiveStatus: String,
    val characterSpecies: String,
    val characterSubspecies: String,
    val characterSex: String,
    val characterBirthPlace: String,
    val lastLocation: String,
    val characterAvatar: String,
)