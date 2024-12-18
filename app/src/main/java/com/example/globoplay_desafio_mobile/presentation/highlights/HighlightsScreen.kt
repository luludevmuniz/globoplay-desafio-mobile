package com.example.globoplay_desafio_mobile.presentation.highlights

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.globoplay_desafio_mobile.R
import com.example.globoplay_desafio_mobile.domain.model.MediaType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HighlightsScreen(
    modifier: Modifier = Modifier,
    highlightsViewModel: HighlightsViewModel = hiltViewModel(),
    onMediaSelected: (Int, MediaType) -> Unit = { _, _ -> },
    onBackClicked: () -> Unit
) {
    val media by highlightsViewModel.media.collectAsStateWithLifecycle()
    val similarMedia by highlightsViewModel.similarMedia.collectAsStateWithLifecycle()
    val isMediaInMyList by highlightsViewModel.isMediaInMyList.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackClicked()
                        },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Botão voltar"
                        )
                    }
                },
                title = {}
            )
        }
    ) { paddingValues ->
        media?.let {
            HighlightsContent(
                modifier = Modifier.padding(paddingValues),
                media = it,
                similarMedia = similarMedia,
                isMediaInMyList = isMediaInMyList,
                onFavoriteClick = {
                    highlightsViewModel.onEvent(HighlightsEvent.SaveMedia(it))
                },
                onUnfavoriteClick = {
                    highlightsViewModel.onEvent(HighlightsEvent.DeleteMedia(it.id))
                },
                onMediaSelected = onMediaSelected
            )
        }
    }
}