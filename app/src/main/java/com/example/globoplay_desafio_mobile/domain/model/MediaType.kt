package com.example.globoplay_desafio_mobile.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class MediaType(val description: String) {
    MOVIE("Cinema"),
    SERIES("Série"),
    SOAP("Novela")
}