package org.ernisernis.ratemoviescmp.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ernisernis.ratemoviescmp.core.domain.util.onError
import org.ernisernis.ratemoviescmp.core.domain.util.onSuccess
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository

// Shared ViewModel to Route.MovieGraph graph
class SelectedMovieViewModel(
    private val movieRepository: MovieRepository,
): ViewModel() {

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie = _selectedMovie.asStateFlow()

    fun onSelectMovie(movie: Movie?) {
        _selectedMovie.value = movie
    }

    fun onSelectMovieId(id: Int) {
        viewModelScope.launch {
            movieRepository
                .getMovie(id)
                .onSuccess { movie ->
                    _selectedMovie.value = movie
                }
                .onError {
                    // TODO implement
                }
        }
    }
}