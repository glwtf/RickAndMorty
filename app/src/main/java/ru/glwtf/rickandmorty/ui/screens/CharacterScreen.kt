package ru.glwtf.rickandmorty.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.glwtf.rickandmorty.R
import ru.glwtf.rickandmorty.domain.entity.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(
    character: Character,
    onBackPressedClickListener: () -> Unit
) {
    Scaffold (
      topBar = {
          TopAppBar(
              title = {},
              colors = TopAppBarDefaults.smallTopAppBarColors(
//                  containerColor = MaterialTheme.colorScheme.background
                  containerColor = Color.Transparent
              ),
              navigationIcon = {
                  FilledTonalIconButton(
                      onClick = { onBackPressedClickListener() },
                      colors = IconButtonDefaults.iconButtonColors(
                          containerColor = MaterialTheme.colorScheme.onSecondary
                      )
                  ) {
                      Icon(
                          imageVector = Icons.Filled.ArrowForward,
                          contentDescription = null,
                          tint = MaterialTheme.colorScheme.onPrimary,
                          modifier = Modifier.rotate(180f)
                      )
                  }
              }
          )
      }
    ) { paddingVal ->
        CharacterScreenContent(
            character = character,
            paddingVal = paddingVal
        )
    }

}
@Composable
private fun CharacterScreenContent(
    character: Character,
    paddingVal: PaddingValues,
) {
    Column (
//        modifier = Modifier.padding(paddingVal)
    ) {
        AsyncImage(
            model = character.characterAvatar,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Column (
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = character.characterName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)

            )
            Row (
                modifier = Modifier.padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                Text(
                    text = "${character.characterLiveStatus} - ${character.characterSpecies}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = "Last known location:",
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = character.lastLocation,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}