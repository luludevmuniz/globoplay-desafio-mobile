package com.example.globoplay_desafio_mobile.data.repository

import com.example.globoplay_desafio_mobile.data.local.Database
import com.example.globoplay_desafio_mobile.data.local.entity.MediaEntity
import com.example.globoplay_desafio_mobile.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(database: Database) : LocalDataSource {
    private val mediaDao = database.mediaDao()

    override fun getAllMedia(): Flow<List<MediaEntity>> = mediaDao.getAllMedia()

    override suspend fun insertMedia(media: MediaEntity) = mediaDao.insertMedia(media)

    override suspend fun deleteMedia(id: Int) = mediaDao.deleteMedia(id)

    override fun isMediaInMyList(id: Int): Flow<Boolean> = mediaDao.isMediaInMyList(id = id)
}