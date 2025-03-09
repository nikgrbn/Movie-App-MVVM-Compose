package com.example.shutterflyassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shutterflyassignment.data.remote.Genre
import com.example.shutterflyassignment.ui.theme.ShutterflyAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShutterflyAssignmentTheme {
                val mainViewModel: MainViewModel = viewModel()

                // Initiate fetching of genres once
                LaunchedEffect(Unit) {
                    mainViewModel.loadGenres()
                }

                // Observe state
                val isLoading = mainViewModel.isLoadingGenres
                val genres = mainViewModel.genres

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Genres") }
                        )
                    }
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        when {
                            isLoading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            else -> {
                                GenreTabRow(genres)
                            }
                        }
                    }
                }
            }
        }
    }
}

// Display the list of genres in a scrollable tab row
@Composable
fun GenreTabRow(genres: List<Genre>) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    if (genres.isNotEmpty()) {
        ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
            genres.forEachIndexed { index, genre ->
                Tab(
                    selected = (index == selectedTabIndex),
                    onClick = { selectedTabIndex = index },
                    text = { Text(genre.name) }
                )
            }
        }
    } else {
        Text("No genres found")
    }
}