package com.example.globoplay_desafio_mobile

import app.cash.turbine.test
import com.example.globoplay_desafio_mobile.domain.model.Movie
import com.example.globoplay_desafio_mobile.domain.model.MovieResponse
import com.example.globoplay_desafio_mobile.domain.model.Series
import com.example.globoplay_desafio_mobile.domain.model.SeriesResponse
import com.example.globoplay_desafio_mobile.domain.use_cases.UseCases
import com.example.globoplay_desafio_mobile.presentation.principal.home.HomeViewModel
import com.example.globoplay_desafio_mobile.presentation.principal.home.MoviesUiState
import com.example.globoplay_desafio_mobile.presentation.principal.home.SeriesUiState
import com.example.globoplay_desafio_mobile.presentation.principal.home.SoapUiState
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val useCases: UseCases = mockk()
    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        every { useCases.getMoviesUseCase() } returns flow {
            emit(
                MovieResponse(
                    page = 1,
                    totalPages = 1,
                    totalResults = 1,
                    results = emptyList()
                )
            )
        }
        every { useCases.getSeriesUseCase() } returns flow {
            emit(
                SeriesResponse(
                    page = 1,
                    totalPages = 1,
                    totalResults = 1,
                    results = emptyList()
                )
            )
        }
        every { useCases.getSoapsUseCase() } returns flow {
            emit(
                SeriesResponse(
                    page = 1,
                    totalPages = 1,
                    totalResults = 1,
                    results = emptyList()
                )
            )
        }

        viewModel = HomeViewModel(useCases)
    }

    @Test
    fun `moviesUiState should emit the correct initial state`() = runTest {
        viewModel.moviesUiState.test {
            val initialState = awaitItem()
            assertEquals(MoviesUiState(), initialState)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `moviesUiState should emit the correct data`() = runTest {
        val expectedMovies = MovieResponse(
            page = 1,
            results = listOf(
                Movie(
                    id = 1,
                    title = "Movie 1",
                    posterPath = "poster_path",
                    adult = false,
                    backdropPath = "",
                    genreIds = listOf(),
                    originalLanguage = "",
                    originalTitle = "",
                    overview = "",
                    popularity = 0.0,
                    releaseDate = "",
                    video = false,
                    voteAverage = 0.0,
                    voteCount = 0
                )
            ),
            totalPages = 1,
            totalResults = 1
        )
        every { useCases.getMoviesUseCase() } returns flow { emit(expectedMovies) }

        viewModel = HomeViewModel(useCases)

        viewModel.moviesUiState.test {

            awaitItem()

            val emittedState = awaitItem()
            assertEquals(
                MoviesUiState(movies = expectedMovies.results, isLoading = false, errorMessage = null),
                emittedState
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `moviesUiState should handle errors gracefully`() = runTest {
        val errorMessage = "Test error"
        every { useCases.getMoviesUseCase() } returns flow {
            throw RuntimeException(errorMessage)
        }

        viewModel = HomeViewModel(useCases)

        viewModel.moviesUiState.test {

            awaitItem()

            val emittedState = awaitItem()
            assertEquals(
                MoviesUiState(
                    movies = emptyList(),
                    isLoading = false,
                    errorMessage = "Erro ao carregar filmes: $errorMessage"
                ),
                emittedState
            )
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `seriesUiState should emit the correct initial state`() = runTest {
        viewModel.seriesUiState.test {
            val initialState = awaitItem()
            assertEquals(SeriesUiState(), initialState) // Verifica o estado inicial
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `soapsUiState should emit the correct data`() = runTest {
        val expectedSoaps = SeriesResponse(
            page = 1,
            results = listOf(
                Series(
                    id = 1,
                    name = "Soap 1",
                    posterPath = "poster_path",
                    adult = false,
                    backdropPath = "",
                    genreIds = listOf(),
                    originalLanguage = "",
                    overview = "",
                    popularity = 0.0,
                    voteAverage = 0.0,
                    firstAirDate = null,
                    voteCount = 0,
                    originalName = "",
                    originCountry = listOf()
                )
            ),
            totalPages = 1,
            totalResults = 1
        )
        every { useCases.getSoapsUseCase() } returns flow { emit(expectedSoaps) }

        viewModel = HomeViewModel(useCases)

        viewModel.soapsUiState.test {

            awaitItem()

            val emittedState = awaitItem()
            assertEquals(
                SoapUiState(soaps = expectedSoaps.results, isLoading = false, errorMessage = null),
                emittedState
            )

            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `soapsUiState should handle errors gracefully`() = runTest {
        val errorMessage = "Test error"
        every { useCases.getSoapsUseCase() } returns flow {
            throw RuntimeException(errorMessage)
        }

        viewModel = HomeViewModel(useCases)

        viewModel.soapsUiState.test {

            awaitItem()

            val emittedState = awaitItem()
            assertEquals(
                SoapUiState(
                    soaps = emptyList(),
                    isLoading = false,
                    errorMessage = "Erro ao carregar novelas: $errorMessage"
                ),
                emittedState
            )
            cancelAndIgnoreRemainingEvents()
        }
    }
}
