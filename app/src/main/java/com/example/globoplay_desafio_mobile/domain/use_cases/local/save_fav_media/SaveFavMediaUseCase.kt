package com.example.globoplay_desafio_mobile.domain.use_cases.local.save_fav_media

import com.example.globoplay_desafio_mobile.data.local.entity.MediaEntity
import com.example.globoplay_desafio_mobile.data.repository.Repository

class SaveFavMediaUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(media: MediaEntity) = repository.insertFavMedia(media = media)
}