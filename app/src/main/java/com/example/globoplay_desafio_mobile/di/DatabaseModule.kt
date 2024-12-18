package com.example.globoplay_desafio_mobile.di

import android.content.Context
import androidx.room.Room
import com.example.globoplay_desafio_mobile.data.local.Database
import com.example.globoplay_desafio_mobile.data.repository.LocalDataSourceImpl
import com.example.globoplay_desafio_mobile.domain.repository.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "globoplay_teste_mobile.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(database: Database): LocalDataSource =
        LocalDataSourceImpl(database = database)
}