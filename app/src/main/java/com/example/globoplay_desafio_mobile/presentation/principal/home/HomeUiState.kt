package com.example.globoplay_desafio_mobile.presentation.principal.home

import com.example.globoplay_desafio_mobile.domain.model.Movie
import com.example.globoplay_desafio_mobile.domain.model.Series

data class MoviesUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

data class SeriesUiState(
    val series: List<Series> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

data class SoapUiState(
    val soaps: List<Series> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)