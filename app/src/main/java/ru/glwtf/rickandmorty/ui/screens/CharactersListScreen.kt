package ru.glwtf.rickandmorty.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import ru.glwtf.rickandmorty.R
import ru.glwtf.rickandmorty.ui.utils.LoadingNextPageItem
import ru.glwtf.rickandmorty.domain.entity.Character

@Composable
fun CharactersListScreen(
    characterPagingItem: LazyPagingItems<Character>,
    onClickListener: (Character) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(characterPagingItem.itemCount) {index ->
            val curCharacter = characterPagingItem[index]
            curCharacter?.let {
                Row (
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    CharacterItemContent(
                        character = curCharacter,
                        onClickListener = onClickListener
                    )
                }
            }
        }
        characterPagingItem.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    item {
                        LoadingNextPageItem(modifier = Modifier)
                    }
                }
                loadState.append is LoadState.Error -> {
                    Log.d("RaM_TAG", "append Error")
                }
            }
        }
    }
}


@Composable
private fun CharacterItemContent(
    character: Character,
    onClickListener: (Character) -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickListener(character) },
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            model = character.characterAvatar,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clip(CircleShape)
                .size(80.dp)
        )
        Column {
            Text(
                text = character.characterName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                val statusColor = when(character.characterLiveStatus) {
                    "Alive" -> Color.Green
                    "Dead" -> Color.Red
                    else -> Color.Gray
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_status),
                    contentDescription = null,
                    tint = statusColor,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(16.dp)
                        .padding(end = 4.dp)

                )
                Text(text = character.characterLiveStatus)
            }
        }
    }
}