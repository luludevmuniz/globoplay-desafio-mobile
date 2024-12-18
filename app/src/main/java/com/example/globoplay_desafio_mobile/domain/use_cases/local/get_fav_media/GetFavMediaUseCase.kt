package com.example.globoplay_desafio_mobile.domain.use_cases.local.get_fav_media

import com.example.globoplay_desafio_mobile.data.repository.Repository

class GetFavMediaUseCase(
    private val repository: Repository
) {
    operator fun invoke() = repository.getFavMedia()
}