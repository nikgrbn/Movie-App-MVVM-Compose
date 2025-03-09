package com.example.shutterflyassignment.ui.genres.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.shutterflyassignment.data.local.Movie

@Composable
fun MovieItem(movie: Movie) {
    Column(
        modifier = Modifier
            .padding(8.dp)  // Add spacing around each movie item
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)  // Keep movie poster's aspect ratio
                .clip(RoundedCornerShape(12.dp))  // Round corners
                .background(MaterialTheme.colorScheme.surfaceVariant) // Placeholder background
        ) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp)) // Add spacing between poster and text

        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 4.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "⭐ ${movie.rating}  |  ${movie.releaseYear}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 4.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}