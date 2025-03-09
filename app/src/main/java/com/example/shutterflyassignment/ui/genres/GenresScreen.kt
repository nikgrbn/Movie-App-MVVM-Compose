package com.example.shutterflyassignment.ui.genres


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shutterflyassignment.ui.genres.composable.MovieItem
import com.example.shutterflyassignment.ui.theme.gradientBackground


@Composable
fun GenresScreen(
    viewModel: GenresScreenViewModel = hiltViewModel()
) {
    val genres = viewModel.genres
    val selectedTabIndex = viewModel.selectedTabIndex
    val movies = viewModel.movies
    val isLoadingGenres = viewModel.isLoadingGenres

    // Load genres when this composable is first shown
    LaunchedEffect(Unit) {
        viewModel.loadGenres()
    }

    // Once genres have arrived, load movies for the first genre
    LaunchedEffect(genres) {
        if (genres.isNotEmpty() && movies.isEmpty()) {
            viewModel.loadMoviesByGenre(genres.first())
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .gradientBackground() // Apply the fixed gradient
    ) {
        Scaffold(
            containerColor = Color.Transparent, // Ensure Scaffold doesn't override the gradient
            topBar = { TopBar() }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when {
                    genres.isNotEmpty() -> {
                        GenreTabs(viewModel)
                        MovieGrid(viewModel)
                    }
                }
            }
        }
    }
}


@Composable
fun GenreTabs(viewModel: GenresScreenViewModel) {
    val genres = viewModel.genres
    val selectedTabIndex = viewModel.selectedTabIndex

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent // Set the container color to transparent
    ) {
        genres.forEachIndexed { index, genre ->
            Tab(
                selected = (index == selectedTabIndex),
                onClick = { viewModel.onTabSelected(index) },
                text = { Text(text = genre.name, color = MaterialTheme.colorScheme.onBackground) }
            )
        }
    }
}


@Composable
fun MovieGrid(viewModel: GenresScreenViewModel) {
    val movies = viewModel.movies

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = { Text("Genres", color = MaterialTheme.colorScheme.onBackground) },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent, // Transparent to blend with gradient
            titleContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}