package com.example.globoplay_desafio_mobile.data.repository

import com.example.globoplay_desafio_mobile.data.remote.ApiService
import com.example.globoplay_desafio_mobile.domain.mapper.toMedia
import com.example.globoplay_desafio_mobile.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {

    override fun getSoaps() = flow {
        emit(apiService.getSoaps())
    }

    override fun getSeries() = flow {
        emit(apiService.getSeries())
    }

    override fun getMovies() = flow {
        emit(apiService.getMovies())
    }

    override fun getMovieDetails(mediaId: Int) = flow {
        emit(apiService.getMovieDetails(mediaId = mediaId).toMedia())
    }

    override fun getSeriesDetails(mediaId: Int) = flow {
        emit(apiService.getSeriesDetails(mediaId = mediaId).toMedia())
    }

    override fun getSimilarMovies(mediaId: Int) = flow {
        emit(
            apiService.getSimilarMovies(mediaId = mediaId).results.map {
                it.toMedia()
            }
        )
    }

    override fun getSimilarSeries(mediaId: Int) = flow {
        emit(
            apiService.getSimilarSeries(mediaId = mediaId).results.map {
                it.toMedia()
            }
        )
    }
}