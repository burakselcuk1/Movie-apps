package com.example.movieapplication.repository

import com.example.movieapplication.common.utils.Constans
import com.example.movieapplication.model.MovieResponse
import com.example.movieapplication.services.MovieServices
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieServices: MovieServices) {

    suspend fun getPopularMovies(page: Int): Result<MovieResponse> {
        return try {
            val response = movieServices.getPopularMovies(Constans.API_KEY, page)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


