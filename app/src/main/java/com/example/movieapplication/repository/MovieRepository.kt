package com.example.movieapplication.repository

import com.example.movieapplication.services.MovieServices

class MovieRepository(private val movieServices: MovieServices) {

    suspend fun getPopularMovies() = movieServices.getPopularMovies()
}
