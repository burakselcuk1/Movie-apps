package com.example.movieapplication.di

import com.example.movieapplication.repository.MovieRepository
import com.example.movieapplication.services.MovieServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMovieRepository(movieServices: MovieServices): MovieRepository {
        return MovieRepository(movieServices)
    }
}