package com.example.globoplay_desafio_mobile.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.globoplay_desafio_mobile.domain.model.MediaType

@Entity
data class MediaEntity(
    @PrimaryKey
    val id: Int,
    val posterPath: String,
    val type: MediaType
)
