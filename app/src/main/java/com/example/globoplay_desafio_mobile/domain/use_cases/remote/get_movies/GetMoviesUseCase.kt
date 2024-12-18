package com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_movies

import com.example.globoplay_desafio_mobile.data.repository.Repository
import com.example.globoplay_desafio_mobile.domain.model.MovieResponse
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<MovieResponse> = repository.getMovies()
}