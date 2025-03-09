package com.example.shutterflyassignment.data.repository

import com.example.shutterflyassignment.BuildConfig
import com.example.shutterflyassignment.data.local.Genre
import com.example.shutterflyassignment.data.local.Movie
import com.example.shutterflyassignment.data.remote.MovieDto
import com.example.shutterflyassignment.domain.model.GenreRepository
import com.example.shutterflyassignment.network.api.TmdbApi
import javax.inject.Inject


class GenreRepositoryImpl @Inject constructor(
    private val tmdbApi: TmdbApi
) : GenreRepository {

    val apiKey : String = BuildConfig.TMDB_API_KEY

    override suspend fun fetchGenres(): List<Genre> {
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

    override suspend fun fetchMoviesByGenre(genre: Genre): List<Movie> {
        val response = tmdbApi.getMoviesByGenre(apiKey, genre.id)
        if (response.isSuccessful) {
            val dtoList = response.body()?.results.orEmpty()
            return dtoList.map { dto: MovieDto ->
                // Parse the year from release date if possible (e.g. "1972-03-14" -> "1972")
                val year = dto.release_date?.takeIf { it.length >= 4 }?.substring(0, 4) ?: "N/A"

                // Hardcode a base URL or fetch from /configuration if you prefer
                val posterFullPath = "https://image.tmdb.org/t/p/w500${dto.poster_path}"
                println("Poster URL: $posterFullPath")

                Movie(
                    id = dto.id ?: 0,
                    title = dto.title.orEmpty(),
                    releaseYear = year,
                    posterPath = posterFullPath,
                    rating = dto.vote_average ?: 0.0
                )
            }
        } else {
            // Log or handle error
            println("Failed to fetch movies: ${response.code()} ${response.message()}")
            return emptyList()
        }
    }
}