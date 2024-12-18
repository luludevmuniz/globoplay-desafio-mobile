package com.example.globoplay_desafio_mobile.presentation.highlights

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.globoplay_desafio_mobile.R
import com.example.globoplay_desafio_mobile.domain.model.Media
import com.example.globoplay_desafio_mobile.domain.model.MediaType
import com.example.globoplay_desafio_mobile.navigation.destinations.TabRowDestination
import com.example.globoplay_desafio_mobile.ui.theme.GloboplayDesafioMobileTheme
import com.example.globoplay_desafio_mobile.ui.theme.Padding

@Composable
fun HighlightsContent(
    modifier: Modifier = Modifier,
    media: Media,
    similarMedia: List<Media>,
    isMediaInMyList: Boolean,
    onWatchClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onUnfavoriteClick: () -> Unit = {},
    onMediaSelected: (Int, MediaType) -> Unit = { _, _ -> }
) {
    var backgroundSize by remember {
        mutableStateOf(
            DpSize(
                0.dp,
                0.dp
            )
        )
    }
    val colorStops = arrayOf(
        0.0f to MaterialTheme.colorScheme.surface,
        0.2f to Color.Transparent,
        0.7f to MaterialTheme.colorScheme.surface,
        1f to MaterialTheme.colorScheme.surface
    )
    val gradient = Brush.verticalGradient(colorStops = colorStops)
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .safeDrawingPadding()
    ) {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(backgroundSize)
                    .blur(20.dp),
                model = "https://image.tmdb.org/t/p/original/${media.posterPath}",
                contentDescription = media.title,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .size(backgroundSize)
                    .background(brush = gradient)
            )
            Column(
                modifier = Modifier
                    .graphicsLayer {
                        backgroundSize = size.toDpSize()
                    }
                    .padding(top = Padding.extraLarge)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .sizeIn(
                            maxWidth = 120.dp,
                            maxHeight = 180.dp
                        )
                        .align(alignment = Alignment.CenterHorizontally),
                    model = "https://image.tmdb.org/t/p/original/${media.posterPath}",
                    contentDescription = media.title,
                )

                HighlightsHeader(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = Padding.large)
                        .padding(horizontal = Padding.large),
                    title = media.title,
                    category = media.type.description
                )
                HighlightsDescription(
                    modifier = Modifier
                        .padding(top = Padding.medium)
                        .padding(horizontal = Padding.large),
                    description = media.overview
                )
                Spacer(modifier = Modifier.height(Padding.medium))
                HighlightsActions(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Padding.medium)
                        .padding(horizontal = Padding.large),
                    isMediaInMyList = isMediaInMyList,
                    onWatchClick = onWatchClick,
                    onFavoriteClick = onFavoriteClick,
                    onUnfavoriteClick = onUnfavoriteClick
                )
                Spacer(modifier = Modifier.height(Padding.medium))
                TabRow(selectedTabIndex = selectedTabIndex) {
                    TabRowDestination.entries.forEachIndexed { index, destination ->
                        Tab(
                            selected = selectedTabIndex == index,
                            text = {
                                Text(
                                    text = destination.title,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Medium
                                )
                            },
                            onClick = {
                                selectedTabIndex = index
                            }
                        )
                    }
                }
            }
        }
        when (selectedTabIndex) {
            0 -> SimilarMedia(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainer)
                    .fillMaxSize()
                    .padding(all = Padding.large),
                similarMedia = similarMedia,
                onMediaSelected = onMediaSelected
            )

            1 -> MediaDetails(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainer)
                    .fillMaxSize()
                    .padding(all = Padding.large),
                media = media
            )
        }
    }
}

@Composable
private fun HighlightsHeader(
    modifier: Modifier = Modifier,
    category: String,
    title: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Padding.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun HighlightsDescription(
    modifier: Modifier = Modifier,
    description: String
) {
    Text(
        modifier = modifier,
        text = description,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun HighlightsActions(
    modifier: Modifier = Modifier,
    isMediaInMyList: Boolean,
    onWatchClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onUnfavoriteClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Padding.small)
    ) {
        FilledTonalButton(
            modifier = Modifier.weight(1f),
            onClick = onWatchClick,
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults
                .filledTonalButtonColors()
                .copy(containerColor = MaterialTheme.colorScheme.onSurface)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Padding.extraSmall)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_play_arrow),
                    contentDescription = "Botão de assistir",
                    tint = MaterialTheme.colorScheme.surfaceBright
                )
                Text(
                    text = "Assista",
                    color = MaterialTheme.colorScheme.surfaceBright
                )
            }
        }
        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = if (isMediaInMyList) onUnfavoriteClick else onFavoriteClick,
            shape = RoundedCornerShape(10),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Padding.extraSmall)
            ) {
                Icon(
                    painter = if (isMediaInMyList) painterResource(R.drawable.ic_check) else painterResource(
                        R.drawable.ic_star
                    ),
                    contentDescription = if (isMediaInMyList) "Botão de desfavoritar" else "Botão de favoritar",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = if (isMediaInMyList) "Adicionado" else "Minha lista",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SimilarMedia(
    modifier: Modifier = Modifier,
    similarMedia: List<Media>,
    onMediaSelected: (Int, MediaType) -> Unit = { _, _ -> }
) {
    if (similarMedia.isEmpty()) {
        Box(
            modifier = modifier.padding(Padding.extraLarge),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Oops... ainda não há nada por aqui!",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    } else {
        FlowRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(Padding.medium)
        ) {
            similarMedia.forEach { media ->
                AsyncImage(
                    modifier = Modifier
                        .sizeIn(
                            maxWidth = 100.dp,
                            maxHeight = 160.dp
                        )
                        .clickable {
                            onMediaSelected(media.id, media.type)
                        },
                    model = "https://image.tmdb.org/t/p/original/${media.posterPath}",
                    contentScale = ContentScale.Fit,
                    contentDescription = media.title
                )
            }
        }
    }
}

@Composable
fun MediaDetails(
    modifier: Modifier = Modifier,
    media: Media
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Padding.extraSmall)
    ) {
        Text(
            modifier = Modifier.padding(vertical = Padding.large),
            text = "Ficha técnica",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        TechSheetItem(
            title = "Título Original: ",
            value = media.title
        )
        TechSheetItem(
            title = "Gênero: ",
            value = media.genres
        )
        media.episodes?.let { episodes ->
            TechSheetItem(
                title = "Episódios: ",
                value = episodes
            )
        }
        media.runtime?.let { runtime ->
            TechSheetItem(
                title = "Duração: ",
                value = runtime
            )
        }
        TechSheetItem(
            title = "Ano de produção: ",
            value = media.year
        )
        TechSheetItem(
            title = "País: ",
            value = media.country
        )
    }
}

@Composable
private fun TechSheetItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(text = title)
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                append(text = value)
            }
        }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HighlightsContentPrev() {
    GloboplayDesafioMobileTheme {
        HighlightsContent(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface),
            media = Media(
                id = 1,
                title = "Orgulho e Paixão",
                type = MediaType.SOAP,
                overview = "'Orgulho e Paixão' tem seus personagens livremmente inspirados no universo da" +
                        "escritora inglesa Jane Austen. É uma história romântica.",
                posterPath = "",
                country = "Brasil",
                genres = "Drama",
                year = "2024"
            ),
            isMediaInMyList = false,
            similarMedia = emptyList()
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MediaDetailsContentPrev() {
    GloboplayDesafioMobileTheme {
        MediaDetails(
            Modifier
                .background(color = MaterialTheme.colorScheme.surfaceContainer),
            media = Media(
                id = 1,
                title = "Orgulho e Paixão",
                type = MediaType.SOAP,
                overview = "'Orgulho e Paixão' tem seus personagens livremmente inspirados no universo da" +
                        "escritora inglesa Jane Austen. É uma história romântica.",
                posterPath = "",
                country = "Brasil",
                genres = "Drama",
                year = "2024"
            )
        )
    }
}