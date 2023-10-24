package ru.glwtf.rickandmorty.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import ru.glwtf.rickandmorty.ui.viewmodels.MainScreenViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
//    val viewModel: MainScreenViewModel = viewModel()
    val characterPagingItem = viewModel.charactersState.collectAsLazyPagingItems()
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
                    Text(text = it.characterName)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = it.characterLiveStatus)
                }
            }
            characterPagingItem.apply {
                when {
                    loadState.append is LoadState.Loading -> {
                        Log.d("RaM_TAG", "$index append Loading")
                    }
                    loadState.append is LoadState.Error -> {
                        Log.d("RaM_TAG", "$index append Error")
                    }
                    loadState.append is LoadState.NotLoading -> {
                        Log.d("RaM_TAG", "$index ${loadState.append}")
                    }
                    loadState.refresh is LoadState.Loading -> {
                        Log.d("RaM_TAG", "$index refresh Loading")
                    }
                    loadState.refresh is LoadState.Error -> {
                        Log.d("RaM_TAG", "$index refresh Error")
                    }
                    loadState.refresh is LoadState.NotLoading -> {
                        Log.d("RaM_TAG", "$index refresh NotLoading")
                    }
                    loadState.prepend is LoadState.Loading -> {
                        Log.d("RaM_TAG", "$index prepend Loading")
                    }
                    loadState.prepend is LoadState.Error -> {
                        Log.d("RaM_TAG", "$index prepend Error")
                    }
                    loadState.prepend is LoadState.NotLoading -> {
                        Log.d("RaM_TAG", "$index prepend NotLoading")
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}