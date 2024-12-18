package com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_similar_media

import com.example.globoplay_desafio_mobile.data.repository.Repository
import com.example.globoplay_desafio_mobile.domain.model.Media
import com.example.globoplay_desafio_mobile.domain.model.MediaType
import kotlinx.coroutines.flow.Flow

class GetSimilarMediaUseCase(
    private val repository: Repository
) {
    operator fun invoke(
        mediaId: Int,
        mediaType: MediaType
    ): Flow<List<Media>> = repository.getSimilarMedia(
        mediaId = mediaId,
        mediaType = mediaType
    )
}