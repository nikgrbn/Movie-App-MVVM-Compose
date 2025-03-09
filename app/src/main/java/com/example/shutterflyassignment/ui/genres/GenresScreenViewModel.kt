package com.example.shutterflyassignment.ui.genres

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shutterflyassignment.data.remote.Genre
import com.example.shutterflyassignment.domain.model.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresScreenViewModel @Inject constructor(
    private val genreRepository: GenreRepository
) : ViewModel() {

    var genres by mutableStateOf(emptyList<Genre>())
        private set

    var isLoadingGenres by mutableStateOf(false)
        private set

    // Stores which genre tab is currently selected
    var selectedTabIndex by mutableStateOf(0)
        private set

    fun loadGenres() {
        viewModelScope.launch {
            isLoadingGenres = true
            genres = genreRepository.fetchGenres()
            isLoadingGenres = false
        }
    }

    fun onTabSelected(index: Int) {
        selectedTabIndex = index
    }
}