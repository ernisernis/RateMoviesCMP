package org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository

class MovieBookmarkViewModel(
    private val movieRepository: MovieRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(MovieBookmarkState())
    val state = _state.asStateFlow()

    init {
        observeBookmarks()
    }

    fun onAction(action: MovieBookmarkAction) {
        when (action) {
            else -> Unit
        }
    }

    private fun observeBookmarks() {
        movieRepository
            .getBookmarkMovies()
            .onEach { bookmarkMovies ->
                _state.update { it.copy(
                    movies = bookmarkMovies
                ) }
            }
            .launchIn(viewModelScope)
    }
}