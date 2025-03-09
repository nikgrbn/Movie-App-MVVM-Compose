package com.example.shutterflyassignment.domain.model

import com.example.shutterflyassignment.data.remote.Genre

interface GenreRepository {
    suspend fun fetchGenres(): List<Genre>
}