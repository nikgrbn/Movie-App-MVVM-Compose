package com.example.shutterflyassignment.ui.genres


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shutterflyassignment.data.remote.Movie
import com.example.shutterflyassignment.ui.genres.composable.MovieItem

@Composable
fun GenresScreen(
    viewModel: GenresScreenViewModel = hiltViewModel()
) {
    val isLoading = viewModel.isLoadingGenres
    val genres = viewModel.genres
    val selectedTabIndex = viewModel.selectedTabIndex

    // Automatically load genres once the composable is visible
    LaunchedEffect(Unit) {
        viewModel.loadGenres()
    }

    // For now, we can just show a dummy list.
    val dummyMovies = remember {
        (1..20).map { index ->
            Movie(id = index, title = "Movie $index", releaseYear = "2023", posterPath = "")
        }
    }

    Scaffold(
        topBar = { TopBar() },
    ) { paddingValues ->
        // The main content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                genres.isEmpty() -> {
                    Text("No genres found", modifier = Modifier.align(Alignment.Center))
                }
                else -> {
                    Column(Modifier.fillMaxSize()) {
                        // Scrollable tab row for all genres
                        ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
                            genres.forEachIndexed { index, genre ->
                                Tab(
                                    selected = (index == selectedTabIndex),
                                    onClick = { viewModel.onTabSelected(index) },
                                    text = { Text(genre.name) }
                                )
                            }
                        }

                        // Show a 2-column grid of movies
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            items(dummyMovies.size) { index ->
                                MovieItem(movie = dummyMovies[index])
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