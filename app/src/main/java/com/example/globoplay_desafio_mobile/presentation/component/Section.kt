package com.example.globoplay_desafio_mobile.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.globoplay_desafio_mobile.domain.model.Media
import com.example.globoplay_desafio_mobile.domain.model.MediaType
import com.example.globoplay_desafio_mobile.presentation.principal.my_list.EmptyScreen
import com.example.globoplay_desafio_mobile.ui.theme.Padding
import com.example.globoplay_desafio_mobile.util.TestingTagsConstants.LOADING_STATE_ITEM
import com.example.globoplay_desafio_mobile.util.TestingTagsConstants.MEDIA_ITEM

@Composable
fun Section(
    modifier: Modifier = Modifier,
    title: String,
    medias: List<Media>,
    isLoading: Boolean,
    onMediaSelected: (Int, MediaType) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = Padding.large)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = Padding.large),
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Medium
        )
        if (isLoading) {
            Row {
                repeat(2) {
                    ShimmerEffect(
                        modifier = Modifier
                            .size(
                                width = 145.dp,
                                height = 160.dp
                            )
                            .padding(horizontal = Padding.large)
                            .testTag(LOADING_STATE_ITEM)
                    )
                }
            }
        } else {
            if (medias.isEmpty()) {
                EmptyScreen(
                    modifier = Modifier.padding(horizontal = Padding.large),
                    textStyle = MaterialTheme.typography.titleLarge
                )
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Padding.medium),
                    contentPadding = PaddingValues(horizontal = Padding.large)
                ) {
                    items(
                        items = medias,
                        key = { media -> media.id }
                    ) { media ->
                        AsyncImage(
                            modifier = Modifier
                                .sizeIn(
                                    maxWidth = 100.dp,
                                    maxHeight = 160.dp
                                )
                                .clickable {
                                    onMediaSelected(media.id, media.type)
                                }
                                .testTag(MEDIA_ITEM),
                            model = "https://image.tmdb.org/t/p/original/${media.posterPath}",
                            contentScale = ContentScale.Crop,
                            contentDescription = media.title,
                        )
                    }
                }
            }
        }
    }
}