package com.example.globoplay_desafio_mobile.presentation.highlights

import com.example.globoplay_desafio_mobile.domain.model.Media

sealed interface HighlightsEvent {
    data class SaveMedia(val media: Media) : HighlightsEvent
    data class DeleteMedia(val id: Int) : HighlightsEvent
}