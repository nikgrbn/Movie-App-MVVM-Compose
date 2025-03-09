package com.example.shutterflyassignment.data.remote

data class DiscoverMoviesResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)