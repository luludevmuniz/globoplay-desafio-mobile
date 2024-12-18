package com.example.globoplay_desafio_mobile.data.remote

import com.example.globoplay_desafio_mobile.data.remote.model.MovieDetailsResponse
import com.example.globoplay_desafio_mobile.data.remote.model.SeriesDetailsResponse
import com.example.globoplay_desafio_mobile.domain.model.MovieResponse
import com.example.globoplay_desafio_mobile.domain.model.SeriesResponse
import com.example.globoplay_desafio_mobile.util.NetworkConstants.BEARER_TOKEN
import com.example.globoplay_desafio_mobile.util.NetworkConstants.DISCOVER_MOVIE_ENDPOINT
import com.example.globoplay_desafio_mobile.util.NetworkConstants.DISCOVER_TV_ENDPOINT
import com.example.globoplay_desafio_mobile.util.NetworkConstants.GLOBO_COMPANY_ID
import com.example.globoplay_desafio_mobile.util.NetworkConstants.LANGUAGE
import com.example.globoplay_desafio_mobile.util.NetworkConstants.MOVIE_DETAILS_ENDPOINT
import com.example.globoplay_desafio_mobile.util.NetworkConstants.SERIES_DETAILS_ENDPOINT
import com.example.globoplay_desafio_mobile.util.NetworkConstants.SIMILAR_MOVIES_ENDPOINT
import com.example.globoplay_desafio_mobile.util.NetworkConstants.SIMILAR_SERIES_ENDPOINT
import com.example.globoplay_desafio_mobile.util.NetworkConstants.SOAP_GENRE_ID
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(DISCOVER_MOVIE_ENDPOINT)
    suspend fun getMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_companies") companiesIds: String = GLOBO_COMPANY_ID,
        @Header("Authorization") authHeader: String = BEARER_TOKEN,
        @Header("accept") accept: String = "application/json"
    ): MovieResponse

    @GET(DISCOVER_TV_ENDPOINT)
    suspend fun getSeries(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("without_genres") genresIds: String = SOAP_GENRE_ID,
        @Query("with_companies") companiesIds: String = GLOBO_COMPANY_ID,
        @Header("Authorization") authHeader: String = BEARER_TOKEN,
        @Header("accept") accept: String = "application/json"
    ): SeriesResponse

    @GET(DISCOVER_TV_ENDPOINT)
    suspend fun getSoaps(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genresIds: String = SOAP_GENRE_ID,
        @Query("with_companies") companiesIds: String = GLOBO_COMPANY_ID,
        @Header("Authorization") authHeader: String = BEARER_TOKEN,
        @Header("accept") accept: String = "application/json"
    ): SeriesResponse

    @GET(MOVIE_DETAILS_ENDPOINT)
    suspend fun getMovieDetails(
        @Path("movie_id") mediaId: Int,
        @Query("language") language: String = LANGUAGE,
        @Header("Authorization") authHeader: String = BEARER_TOKEN,
        @Header("accept") accept: String = "application/json"
    ): MovieDetailsResponse

    @GET(SERIES_DETAILS_ENDPOINT)
    suspend fun getSeriesDetails(
        @Path("series_id") mediaId: Int,
        @Query("language") language: String = LANGUAGE,
        @Header("Authorization") authHeader: String = BEARER_TOKEN,
        @Header("accept") accept: String = "application/json"
    ): SeriesDetailsResponse

    @GET(SIMILAR_MOVIES_ENDPOINT)
    suspend fun getSimilarMovies(
        @Path("movie_id") mediaId: Int,
        @Query("language") language: String = LANGUAGE,
        @Header("Authorization") authHeader: String = BEARER_TOKEN,
        @Header("accept") accept: String = "application/json"
    ): MovieResponse

    @GET(SIMILAR_SERIES_ENDPOINT)
    suspend fun getSimilarSeries(
        @Path("series_id") mediaId: Int,
        @Query("language") language: String = LANGUAGE,
        @Header("Authorization") authHeader: String = BEARER_TOKEN,
        @Header("accept") accept: String = "application/json"
    ): SeriesResponse
}