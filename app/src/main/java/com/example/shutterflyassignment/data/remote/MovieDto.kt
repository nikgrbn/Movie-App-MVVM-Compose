package com.example.shutterflyassignment.data.remote

data class MovieDto(
    val id: Int?,
    val title: String?,
    val release_date: String?,
    val poster_path: String?,
    val vote_average: Double?
)