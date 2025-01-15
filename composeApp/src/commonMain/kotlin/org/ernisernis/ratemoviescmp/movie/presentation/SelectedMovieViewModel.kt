package org.ernisernis.ratemoviescmp.movie.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.ernisernis.ratemoviescmp.movie.domain.Movie

// Shared ViewModel to Route.MovieGraph graph
class SelectedMovieViewModel: ViewModel() {

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie = _selectedMovie.asStateFlow()

    fun onSelectMovie(movie: Movie?) {
        _selectedMovie.value = movie
    }
}