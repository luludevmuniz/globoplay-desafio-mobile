package com.example.globoplay_desafio_mobile.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id: Int,
    val title: String,
    val type: MediaType,
    val overview: String,
    val country: String = "",
    val posterPath: String,
    val runtime: String? = null,
    val episodes: String? = null,
    val genres: String = "",
    val year: String = ""
)
