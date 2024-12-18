package com.example.globoplay_desafio_mobile.data.repository

import com.example.globoplay_desafio_mobile.data.local.entity.MediaEntity
import com.example.globoplay_desafio_mobile.domain.model.Media
import com.example.globoplay_desafio_mobile.domain.model.MediaType
import com.example.globoplay_desafio_mobile.domain.model.MovieResponse
import com.example.globoplay_desafio_mobile.domain.model.SeriesResponse
import com.example.globoplay_desafio_mobile.domain.repository.LocalDataSource
import com.example.globoplay_desafio_mobile.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository
@Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    fun getSoaps(): Flow<SeriesResponse> = remoteDataSource.getSoaps()

    fun getSeries(): Flow<SeriesResponse> = remoteDataSource.getSeries()

    fun getMovies(): Flow<MovieResponse> = remoteDataSource.getMovies()

    fun getMediaDetails(mediaId: Int, mediaType: MediaType): Flow<Media> =
        if (mediaType == MediaType.MOVIE)
            remoteDataSource.getMovieDetails(mediaId) else
            remoteDataSource.getSeriesDetails(mediaId)

    fun getFavMedia(): Flow<List<MediaEntity>> = localDataSource.getAllMedia()

    suspend fun insertFavMedia(media: MediaEntity) = localDataSource.insertMedia(media)

    suspend fun deleteFavMedia(id: Int) = localDataSource.deleteMedia(id)

    fun checkFavMedia(id: Int): Flow<Boolean> = localDataSource.isMediaInMyList(id = id)

    fun getSimilarMedia(mediaId: Int, mediaType: MediaType): Flow<List<Media>> =
        if (mediaType == MediaType.MOVIE)
        remoteDataSource.getSimilarMovies(mediaId) else
            remoteDataSource.getSimilarSeries(mediaId)
}