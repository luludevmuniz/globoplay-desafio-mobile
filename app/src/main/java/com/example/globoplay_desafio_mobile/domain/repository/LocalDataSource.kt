package com.example.globoplay_desafio_mobile.domain.repository

import com.example.globoplay_desafio_mobile.data.local.entity.MediaEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllMedia(): Flow<List<MediaEntity>>
    suspend fun insertMedia(media: MediaEntity)
    suspend fun deleteMedia(id: Int)
    fun isMediaInMyList(id: Int): Flow<Boolean>
}