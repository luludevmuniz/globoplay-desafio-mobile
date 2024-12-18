package com.example.globoplay_desafio_mobile.presentation.principal.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.globoplay_desafio_mobile.domain.model.Media
import com.example.globoplay_desafio_mobile.domain.model.MediaType
import com.example.globoplay_desafio_mobile.presentation.component.Section
import com.example.globoplay_desafio_mobile.ui.theme.GloboplayDesafioMobileTheme
import com.example.globoplay_desafio_mobile.ui.theme.Padding

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onError: (String) -> Unit = {},
    onMediaSelected: (Int, MediaType) -> Unit = {_, _ ->}
) {
    val soapsUiState by viewModel.soapsUiState.collectAsStateWithLifecycle()
    val seriesUiState by viewModel.seriesUiState.collectAsStateWithLifecycle()
    val moviesUiState by viewModel.moviesUiState.collectAsStateWithLifecycle()
    LaunchedEffect(moviesUiState.isLoading) {
        moviesUiState.errorMessage?.isNotBlank()?.let {
            onError(moviesUiState.errorMessage.orEmpty())
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceContainer)
            .padding(vertical = Padding.large),
        verticalArrangement = Arrangement.spacedBy(space = Padding.large)
    ) {
        Section(
            modifier = Modifier.padding(top = Padding.medium),
            title = "Novelas",
            medias = soapsUiState.soaps.map {
                Media(
                    id = it.id,
                    title = it.name.orEmpty(),
                    type = MediaType.SOAP,
                    overview = it.overview,
                    posterPath = it.posterPath.orEmpty()
                )
            },
            isLoading = soapsUiState.isLoading,
            onMediaSelected = onMediaSelected
        )
        Section(
            modifier = Modifier.padding(top = Padding.medium),
            title = "Séries",
            medias = seriesUiState.series.map {
                Media(
                    id = it.id,
                    title = it.name.orEmpty(),
                    type = MediaType.SERIES,
                    overview = it.overview,
                    posterPath = it.posterPath.orEmpty()
                )
            },
            isLoading = seriesUiState.isLoading,
            onMediaSelected = onMediaSelected
        )
        Section(
            modifier = Modifier.padding(top = Padding.medium),
            title = "Cinema",
            medias = moviesUiState.movies.map {
                Media(
                    id = it.id,
                    title = it.title,
                    type = MediaType.MOVIE,
                    overview = it.overview,
                    posterPath = it.posterPath.orEmpty()
                )
            },
            isLoading = moviesUiState.isLoading,
            onMediaSelected = onMediaSelected
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeContentPrev() {
    GloboplayDesafioMobileTheme {
        HomeContent()
    }
}