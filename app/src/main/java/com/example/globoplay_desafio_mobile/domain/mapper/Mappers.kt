package com.example.globoplay_desafio_mobile.domain.mapper

import com.example.globoplay_desafio_mobile.data.local.entity.MediaEntity
import com.example.globoplay_desafio_mobile.data.remote.model.MovieDetailsResponse
import com.example.globoplay_desafio_mobile.data.remote.model.SeriesDetailsResponse
import com.example.globoplay_desafio_mobile.domain.model.Media
import com.example.globoplay_desafio_mobile.domain.model.MediaType
import com.example.globoplay_desafio_mobile.domain.model.Movie
import com.example.globoplay_desafio_mobile.domain.model.Series

fun Series.toMedia() = Media(
    id = id,
    title = name.toString(),
    type = MediaType.SERIES,
    overview = overview,
    country = originCountry.joinToString(),
    posterPath = posterPath.toString(),
    runtime = null,
    year = firstAirDate?.substringBefore("-").orEmpty(),
)

fun Movie.toMedia() = Media(
    id = id,
    title = title.toString(),
    type = MediaType.MOVIE,
    overview = overview,
    posterPath = posterPath.toString()
)

fun MovieDetailsResponse.toMedia() = Media(
    id = id,
    title = title,
    type = MediaType.MOVIE,
    overview = overview,
    country = originCountry.joinToString(),
    posterPath = posterPath.toString(),
    runtime = runtime.toString(),
    episodes = null,
    genres = genres.joinToString { it.name },
    year = releaseDate.substringBefore("-"),
)

fun SeriesDetailsResponse.toMedia() = Media(
    id = id,
    title = name,
    type = MediaType.SERIES,
    overview = overview,
    country = originCountry.joinToString(),
    posterPath = posterPath.toString(),
    runtime = null,
    episodes = numberOfEpisodes.toString(),
    genres = genres.joinToString { it.name },
    year = firstAirDate.substringBefore("-"),
)

fun Media.toEntity() = MediaEntity(
    id = id,
    posterPath = posterPath,
    type = type
)