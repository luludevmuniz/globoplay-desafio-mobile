package com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_series

import com.example.globoplay_desafio_mobile.data.repository.Repository
import com.example.globoplay_desafio_mobile.domain.model.SeriesResponse
import kotlinx.coroutines.flow.Flow

class GetSeriesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<SeriesResponse> = repository.getSeries()
}