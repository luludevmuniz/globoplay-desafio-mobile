package com.example.globoplay_desafio_mobile.domain.repository

import com.example.globoplay_desafio_mobile.domain.model.Media
import com.example.globoplay_desafio_mobile.domain.model.MovieResponse
import com.example.globoplay_desafio_mobile.domain.model.SeriesResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getSoaps(): Flow<SeriesResponse>
    fun getSeries(): Flow<SeriesResponse>
    fun getMovies(): Flow<MovieResponse>
    fun getMovieDetails(mediaId: Int): Flow<Media>
    fun getSeriesDetails(mediaId: Int): Flow<Media>
    fun getSimilarMovies(mediaId: Int): Flow<List<Media>>
    fun getSimilarSeries(mediaId: Int): Flow<List<Media>>
}
