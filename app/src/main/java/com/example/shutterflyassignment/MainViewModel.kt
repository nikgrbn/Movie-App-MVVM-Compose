package com.example.shutterflyassignment

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
class MainViewModel @Inject constructor(
    private val genreRepository: GenreRepository
) : ViewModel() {

    // UI State
    var genres by mutableStateOf<List<Genre>>(emptyList())
        private set

    var isLoadingGenres by mutableStateOf(false)
        private set


    // Load genres from the repository
    fun loadGenres() {
        viewModelScope.launch {
            isLoadingGenres = true

            // Fetch genres from the repository
            genres = genreRepository.fetchGenres()

            isLoadingGenres = false
        }
    }
}