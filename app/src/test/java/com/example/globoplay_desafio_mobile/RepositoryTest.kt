package com.example.globoplay_desafio_mobile

import app.cash.turbine.test
import com.example.globoplay_desafio_mobile.data.local.entity.MediaEntity
import com.example.globoplay_desafio_mobile.data.repository.Repository
import com.example.globoplay_desafio_mobile.domain.model.Media
import com.example.globoplay_desafio_mobile.domain.model.MediaType
import com.example.globoplay_desafio_mobile.domain.model.MovieResponse
import com.example.globoplay_desafio_mobile.domain.model.SeriesResponse
import com.example.globoplay_desafio_mobile.domain.repository.LocalDataSource
import com.example.globoplay_desafio_mobile.domain.repository.RemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoryTest {

    private val remoteDataSource: RemoteDataSource = mockk()
    private val localDataSource: LocalDataSource = mockk()
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = Repository(remoteDataSource, localDataSource)
    }

    @Test
    fun `getMovies should emit the expected MovieResponse`() = runTest {
        val expectedResponse = MovieResponse(
            page = 1,
            results = emptyList(),
            totalPages = 1,
            totalResults = 0
        )
        coEvery { remoteDataSource.getMovies() } returns flow {
            emit(expectedResponse)
        }

        repository.getMovies().test {
            assertEquals(awaitItem(), expectedResponse)
            awaitComplete()
        }

        coVerify { remoteDataSource.getMovies() }
    }

    @Test
    fun `getSoaps should emit the expected SeriesResponse`() = runTest {
        val expectedResponse = SeriesResponse(
            page = 1,
            results = emptyList(),
            totalPages = 1,
            totalResults = 0
        )
        coEvery { remoteDataSource.getSoaps() } returns flow {
            emit(expectedResponse)
        }

        repository.getSoaps().test {
            assertEquals(awaitItem(), expectedResponse)
            awaitComplete()
        }

        coVerify { remoteDataSource.getSoaps() }
    }

    @Test
    fun `getSeries should emit the expected SeriesResponse`() = runTest {
        val expectedResponse = SeriesResponse(
            page = 1,
            results = emptyList(),
            totalPages = 1,
            totalResults = 0
        )
        coEvery { remoteDataSource.getSeries() } returns flow {
            emit(expectedResponse)
        }

        repository.getSeries().test {
            assertEquals(awaitItem(), expectedResponse)
            awaitComplete()
        }

        coVerify { remoteDataSource.getSeries() }
    }

    @Test
    fun `getMediaDetails should emit the correct Media for movies`() = runTest {
        val mediaId = 1
        val expectedMedia = Media(
            id = mediaId,
            title = "Movie",
            posterPath = "poster_path",
            overview = "overview",
            type = MediaType.MOVIE
        )

        coEvery { remoteDataSource.getMovieDetails(mediaId) } returns flow {
            emit(expectedMedia)
        }

        repository.getMediaDetails(mediaId, MediaType.MOVIE).test {
            assertEquals(awaitItem(), expectedMedia)
            awaitComplete()
        }

        coVerify { remoteDataSource.getMovieDetails(mediaId) }
    }

    @Test
    fun `getMediaDetails should emit the correct Media for series`() = runTest {
        val mediaId = 1
        val expectedMedia = Media(
            id = mediaId,
            title = "Series",
            posterPath = "poster_path",
            overview = "overview",
            type = MediaType.SERIES
        )

        coEvery { remoteDataSource.getSeriesDetails(mediaId) } returns flow {
            emit(expectedMedia)
        }

        repository.getMediaDetails(mediaId, MediaType.SERIES).test {
            assertEquals(awaitItem(), expectedMedia)
            awaitComplete()
        }

        coVerify { remoteDataSource.getSeriesDetails(mediaId) }
    }

    @Test
    fun `getFavMedia should emit the correct list of MediaEntity`() = runTest {
        val expectedMediaList = listOf(
            MediaEntity(
                id = 1,
                type = MediaType.SERIES,
                posterPath = "poster_path"
            )
        )

        coEvery { localDataSource.getAllMedia() } returns flow {
            emit(expectedMediaList)
        }

        repository.getFavMedia().test {
            assertEquals(awaitItem(), expectedMediaList)
            awaitComplete()
        }

        coVerify { localDataSource.getAllMedia() }
    }

    @Test
    fun `insertFavMedia should call the local data source`() = runTest {
        val mediaEntity = MediaEntity(
            id = 1,
            type = MediaType.SERIES,
            posterPath = "poster_path"
        )

        coEvery { localDataSource.insertMedia(mediaEntity) } returns Unit

        repository.insertFavMedia(mediaEntity)

        coVerify { localDataSource.insertMedia(mediaEntity) }
    }

    @Test
    fun `deleteFavMedia should call the local data source`() = runTest {
        val mediaId = 1

        coEvery { localDataSource.deleteMedia(mediaId) } returns Unit

        repository.deleteFavMedia(mediaId)

        coVerify { localDataSource.deleteMedia(mediaId) }
    }

    @Test
    fun `checkFavMedia should emit the correct value`() = runTest {
        val mediaId = 1
        val isInList = true

        coEvery { localDataSource.isMediaInMyList(mediaId) } returns flow {
            emit(isInList)
        }

        repository.checkFavMedia(mediaId).test {
            assertEquals(awaitItem(), isInList)
            awaitComplete()
        }

        coVerify { localDataSource.isMediaInMyList(mediaId) }
    }

    @Test
    fun `getSimilarMedia should emit the correct similar movies`() = runTest {
        val mediaId = 1
        val similarMovies = listOf(
            Media(
                id = 2,
                title = "Similar Movies",
                posterPath = "poster_path",
                overview = "overview",
                type = MediaType.MOVIE
            )
        )

        coEvery { remoteDataSource.getSimilarMovies(mediaId) } returns flow {
            emit(similarMovies)
        }

        repository.getSimilarMedia(mediaId, MediaType.MOVIE).test {
            assertEquals(awaitItem(), similarMovies)
            awaitComplete()
        }

        coVerify { remoteDataSource.getSimilarMovies(mediaId) }
    }

    @Test
    fun `getSimilarMedia should emit the correct similar series`() = runTest {
        val mediaId = 1
        val similarSeries = listOf(
            Media(
                id = 3,
                title = "Similar Series",
                posterPath = "poster_path",
                overview = "overview",
                type = MediaType.MOVIE
            )
        )

        coEvery { remoteDataSource.getSimilarSeries(mediaId) } returns flow {
            emit(similarSeries)
        }

        repository.getSimilarMedia(mediaId, MediaType.SERIES).test {
            assertEquals(awaitItem(), similarSeries)
            awaitComplete()
        }

        coVerify { remoteDataSource.getSimilarSeries(mediaId) }
    }
}
