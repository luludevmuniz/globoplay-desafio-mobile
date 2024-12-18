package com.example.globoplay_desafio_mobile.navigation.destinations

import com.example.globoplay_desafio_mobile.domain.model.MediaType
import kotlinx.serialization.Serializable

@Serializable
object Principal

@Serializable
data class Highlights(
    val mediaId: Int,
    val mediaType: MediaType
)