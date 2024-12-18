package com.example.globoplay_desafio_mobile.presentation.principal.my_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.globoplay_desafio_mobile.domain.model.MediaType
import com.example.globoplay_desafio_mobile.ui.theme.Padding

@Composable
fun MyListContent(
    modifier: Modifier = Modifier,
    viewModel: MyListViewModel = hiltViewModel(),
    onMediaSelected: (Int, MediaType) -> Unit = {_, _ ->}
) {
    val medias by viewModel.medias.collectAsStateWithLifecycle()
    if (medias.isEmpty()) {
        EmptyScreen(
            modifier = modifier.padding(Padding.extraLarge),
            textStyle = MaterialTheme.typography.headlineLarge
        )
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(Padding.large),
            verticalArrangement = Arrangement.spacedBy(Padding.large),
            contentPadding = PaddingValues(Padding.large)
        ) {
            items(
                items = medias,
                key = { it.id }
            ) {
                AsyncImage(
                    modifier = Modifier.clickable {
                        onMediaSelected(it.id, it.type)
                    },
                    model = "https://image.tmdb.org/t/p/original/${it.posterPath}",
                    contentScale = ContentScale.Fit,
                    contentDescription = "Imagem do filme"
                )
            }
        }
    }
}

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Oops... ainda não há nada por aqui!",
            style = textStyle
        )
    }
}