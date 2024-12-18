package com.example.globoplay_desafio_mobile.domain.use_cases

import com.example.globoplay_desafio_mobile.domain.use_cases.local.check_fav_media.CheckFavMediaUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.local.delete_fav_media.DeleteFavMediaUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.local.get_fav_media.GetFavMediaUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.local.save_fav_media.SaveFavMediaUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_media_details.GetMediaDetailsUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_movies.GetMoviesUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_series.GetSeriesUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_similar_media.GetSimilarMediaUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_soaps.GetSoapsUseCase

data class UseCases(
    val getMoviesUseCase: GetMoviesUseCase,
    val getSeriesUseCase: GetSeriesUseCase,
    val getSoapsUseCase: GetSoapsUseCase,
    val getMediaDetailsUseCase: GetMediaDetailsUseCase,
    val saveFavMediaUseCase: SaveFavMediaUseCase,
    val deleteFavMediaUseCase: DeleteFavMediaUseCase,
    val getFavMediaUseCase: GetFavMediaUseCase,
    val checkFavMediaUseCase: CheckFavMediaUseCase,
    val getSimilarMediaUseCase: GetSimilarMediaUseCase,
)