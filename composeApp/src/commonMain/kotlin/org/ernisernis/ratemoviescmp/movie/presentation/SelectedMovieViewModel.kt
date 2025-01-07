package org.ernisernis.ratemoviescmp.movie.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi

// Shared ViewModel to Route.MovieGraph graph
class SelectedMovieViewModel: ViewModel() {

    private val _selectedMovie = MutableStateFlow<MovieUi?>(null)
    val selectedMovie = _selectedMovie.asStateFlow()

    fun onSelectMovie(movieUi: MovieUi?) {
        _selectedMovie.value = movieUi
    }
}