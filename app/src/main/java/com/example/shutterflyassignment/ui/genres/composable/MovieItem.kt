package com.example.shutterflyassignment.ui.genres.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.example.shutterflyassignment.data.local.Movie

@Composable
fun MovieItem(movie: Movie) {
    Column() {
        // Poster
        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f/3f)
        ) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
        }
        Text(text = movie.title)
        Text(text = movie.releaseYear)
        Text(text = "Score: ${movie.rating}")
    }
}