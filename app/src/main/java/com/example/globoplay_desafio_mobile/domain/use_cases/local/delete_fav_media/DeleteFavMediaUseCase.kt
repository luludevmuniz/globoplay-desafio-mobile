package com.example.globoplay_desafio_mobile.domain.use_cases.local.delete_fav_media

import com.example.globoplay_desafio_mobile.data.repository.Repository

class DeleteFavMediaUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int) = repository.deleteFavMedia(id = id)
}