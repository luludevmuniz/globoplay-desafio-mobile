package com.example.globoplay_desafio_mobile.domain.use_cases.local.check_fav_media

import com.example.globoplay_desafio_mobile.data.repository.Repository

class CheckFavMediaUseCase(
    private val repository: Repository
) {
    operator fun invoke(id: Int) = repository.checkFavMedia(id = id)
}