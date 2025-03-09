package com.example.shutterflyassignment.di

import com.example.shutterflyassignment.data.repository.GenreRepositoryImpl
import com.example.shutterflyassignment.domain.model.GenreRepository
import com.example.shutterflyassignment.network.api.TmdbApi
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
    fun provideGenreRepository(
        tmdbApi: TmdbApi
    ): GenreRepository {
        return GenreRepositoryImpl(tmdbApi)
    }
}