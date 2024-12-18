package com.example.globoplay_desafio_mobile.presentation.principal.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globoplay_desafio_mobile.domain.model.MovieResponse
import com.example.globoplay_desafio_mobile.domain.model.SeriesResponse
import com.example.globoplay_desafio_mobile.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    useCases: UseCases
) : ViewModel() {
    val moviesUiState: StateFlow<MoviesUiState> = useCases
        .getMoviesUseCase()
        .map { movieResponse: MovieResponse ->
            MoviesUiState(
                movies = movieResponse.results,
                isLoading = false,
                errorMessage = null
            )
        }
        .catch { exception ->
            emit(
                MoviesUiState(
                    movies = emptyList(),
                    isLoading = false,
                    errorMessage = "Erro ao carregar filmes: ${exception.message}"
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = MoviesUiState()
        )

    val seriesUiState: StateFlow<SeriesUiState> = useCases
        .getSeriesUseCase()
        .map { seriesResponse: SeriesResponse ->
            SeriesUiState(
                series = seriesResponse.results,
                isLoading = false,
                errorMessage = null
            )
        }
        .catch { exception ->
            emit(
                SeriesUiState(
                    series = emptyList(),
                    isLoading = false,
                    errorMessage = "Erro ao carregar séries: ${exception.message}"
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = SeriesUiState()
        )

    val soapsUiState: StateFlow<SoapUiState> = useCases
        .getSoapsUseCase()
        .map { soapsResponse: SeriesResponse ->
            SoapUiState(
                soaps = soapsResponse.results,
                isLoading = false,
                errorMessage = null
            )
        }
        .catch { exception ->
            emit(
                SoapUiState(
                    soaps = emptyList(),
                    isLoading = false,
                    errorMessage = "Erro ao carregar novelas: ${exception.message}"
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = SoapUiState()
        )

}