package com.example.shutterflyassignment.network.api

import com.example.shutterflyassignment.data.remote.Genre
import com.example.shutterflyassignment.data.remote.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TmdbApi {
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String
    ): Response<GenreResponse>
}