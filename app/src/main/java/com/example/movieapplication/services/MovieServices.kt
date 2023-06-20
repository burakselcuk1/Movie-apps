package com.example.movieapplication.services

import com.example.movieapplication.model.MovieResponse
import com.example.movieapplication.common.utils.Constans
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServices {


    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String = Constans.API_KEY, @Query("page") page: Int): MovieResponse

}