package com.example.shutterflyassignment.ui.genres


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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shutterflyassignment.ui.genres.composable.MovieItem


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

    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoadingGenres -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                genres.isEmpty() -> {
                    Text("No genres found", Modifier.align(Alignment.Center))
                }
                else -> {
                    // Show tab row and 2-col grid of movies
                    Column(Modifier.fillMaxSize()) {
                        ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
                            genres.forEachIndexed { index, genre ->
                                Tab(
                                    selected = (index == selectedTabIndex),
                                    onClick = { viewModel.onTabSelected(index) },
                                    text = { Text(genre.name) }
                                )
                            }
                        }

                        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                            items(movies) { movie ->
                                MovieItem(movie)
                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = { Text("Genres") },
        navigationIcon = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
            }
        }
    )
}