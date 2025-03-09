package com.example.shutterflyassignment.network.api

import com.example.shutterflyassignment.data.remote.DiscoverMoviesResponse
import com.example.shutterflyassignment.data.remote.GenresResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String
    ): Response<GenresResponse>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: Int
    ): Response<DiscoverMoviesResponse>
}