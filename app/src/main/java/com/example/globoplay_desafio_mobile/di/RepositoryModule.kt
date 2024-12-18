package com.example.globoplay_desafio_mobile.di

import com.example.globoplay_desafio_mobile.data.repository.Repository
import com.example.globoplay_desafio_mobile.domain.use_cases.UseCases
import com.example.globoplay_desafio_mobile.domain.use_cases.local.check_fav_media.CheckFavMediaUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.local.delete_fav_media.DeleteFavMediaUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.local.get_fav_media.GetFavMediaUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.local.save_fav_media.SaveFavMediaUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_media_details.GetMediaDetailsUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_movies.GetMoviesUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_series.GetSeriesUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_similar_media.GetSimilarMediaUseCase
import com.example.globoplay_desafio_mobile.domain.use_cases.remote.get_soaps.GetSoapsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository) = UseCases(
        getMoviesUseCase = GetMoviesUseCase(repository = repository),
        getSeriesUseCase = GetSeriesUseCase(repository = repository),
        getSoapsUseCase = GetSoapsUseCase(repository = repository),
        getMediaDetailsUseCase = GetMediaDetailsUseCase(repository = repository),
        saveFavMediaUseCase = SaveFavMediaUseCase(repository = repository),
        deleteFavMediaUseCase = DeleteFavMediaUseCase(repository = repository),
        getFavMediaUseCase = GetFavMediaUseCase(repository = repository),
        checkFavMediaUseCase = CheckFavMediaUseCase(repository = repository),
        getSimilarMediaUseCase = GetSimilarMediaUseCase(repository = repository),
    )
}