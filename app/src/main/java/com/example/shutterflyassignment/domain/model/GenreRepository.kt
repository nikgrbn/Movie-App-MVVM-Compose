package com.example.shutterflyassignment.domain.model

import com.example.shutterflyassignment.data.local.Genre
import com.example.shutterflyassignment.data.local.Movie

interface GenreRepository {
    suspend fun fetchGenres(): List<Genre>

    suspend fun fetchMoviesByGenre(genre: Genre): List<Movie>
}