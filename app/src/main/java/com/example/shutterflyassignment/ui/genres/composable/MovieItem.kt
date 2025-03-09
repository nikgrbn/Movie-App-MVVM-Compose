package com.example.shutterflyassignment.ui.genres.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shutterflyassignment.data.remote.Movie

@Composable
fun MovieItem(movie: Movie) {
    Column(modifier = Modifier.padding(8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
        ) {
            Text("Poster Placeholder", Modifier.align(Alignment.Center))
        }
        Text(movie.title, style = MaterialTheme.typography.bodyLarge)
        Text(movie.releaseYear, style = MaterialTheme.typography.bodyMedium)
    }
}