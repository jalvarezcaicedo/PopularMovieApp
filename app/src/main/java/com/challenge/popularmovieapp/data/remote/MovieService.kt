package com.challenge.popularmovieapp.data.remote

import com.challenge.popularmovieapp.data.model.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query(value = "page") page: Int?): Response<PopularMoviesResponse>

}