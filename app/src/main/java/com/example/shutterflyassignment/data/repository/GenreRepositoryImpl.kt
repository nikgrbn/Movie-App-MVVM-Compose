package com.example.shutterflyassignment.data.repository

import com.example.shutterflyassignment.BuildConfig
import com.example.shutterflyassignment.data.remote.Genre
import com.example.shutterflyassignment.domain.model.GenreRepository
import com.example.shutterflyassignment.network.api.TmdbApi
import javax.inject.Inject


class GenreRepositoryImpl @Inject constructor(
    private val tmdbApi: TmdbApi
) : GenreRepository {

    override suspend fun fetchGenres(): List<Genre> {
        val apiKey = BuildConfig.TMDB_API_KEY

        val response = tmdbApi.getGenres(apiKey)
        if (response.isSuccessful) {
            val genreList = response.body()?.genres.orEmpty()
            println("Fetched Genres: $genreList")
            return genreList
        } else {
            println("Failed to fetch genres: ${response.code()} ${response.message()}")
            return emptyList()
        }
    }
}