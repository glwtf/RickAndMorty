package ru.glwtf.rickandmorty.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import ru.glwtf.rickandmorty.domain.entity.Character

@Composable
fun CharacterScreen(
    character: Character
) {
    Column {
        AsyncImage(
            model = character.characterAvatar,
            contentDescription = null
        )
        Text(text = character.characterName)
        Text(text = character.characterLiveStatus)
        Text(text = character.characterSex)
        Text(text = character.characterSpecies)
        Text(text = character.characterBirthPlace)
        Text(text = character.lastLocation)
    }
}