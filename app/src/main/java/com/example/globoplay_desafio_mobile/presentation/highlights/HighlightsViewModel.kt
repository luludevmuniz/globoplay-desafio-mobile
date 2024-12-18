package com.example.globoplay_desafio_mobile.presentation.highlights

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globoplay_desafio_mobile.domain.mapper.toEntity
import com.example.globoplay_desafio_mobile.domain.model.Media
import com.example.globoplay_desafio_mobile.domain.model.MediaType
import com.example.globoplay_desafio_mobile.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighlightsViewModel
@Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCases: UseCases
) : ViewModel() {
    val mediaId = savedStateHandle.get<Int>("mediaId") ?: 0
    val mediaType = savedStateHandle.get<MediaType>("mediaType") ?: MediaType.MOVIE
    val media: StateFlow<Media?> = useCases.getMediaDetailsUseCase(
        mediaId = mediaId,
        mediaType = mediaType
    ).map {
        it.copy(type = mediaType)
    }.catch { exception ->
        exception.printStackTrace()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = null
    )
    val similarMedia: StateFlow<List<Media>> = useCases.getSimilarMediaUseCase(
        mediaId = mediaId,
        mediaType = mediaType
    ).map {
        it.map {
            it.copy(type = mediaType)
        }
    }.catch { exception ->
        exception.printStackTrace()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    val isMediaInMyList: StateFlow<Boolean> = useCases.checkFavMediaUseCase(id = mediaId)
        .catch { exception ->
            exception.printStackTrace()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = false
        )

    fun onEvent(event: HighlightsEvent) {
        when (event) {
            is HighlightsEvent.SaveMedia -> saveMedia(event.media)
            is HighlightsEvent.DeleteMedia -> deleteMedia(event.id)
        }
    }

    fun saveMedia(media: Media) {
        viewModelScope.launch {
            useCases.saveFavMediaUseCase(media = media.toEntity())
        }
    }

    fun deleteMedia(id: Int) {
        viewModelScope.launch {
            useCases.deleteFavMediaUseCase(id = id)
        }
    }
}