package org.ernisernis.ratemoviescmp.movie.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ernisernis.ratemoviescmp.core.domain.util.onError
import org.ernisernis.ratemoviescmp.core.domain.util.onSuccess
import org.ernisernis.ratemoviescmp.core.presentation.toUiText
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieUi
import kotlin.coroutines.coroutineContext

class MovieListViewModel(
    private val bookRepository: MovieRepository
): ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            bookRepository
                .getNowPlayingMovies()
                .onSuccess { nowPlayingMovies ->
                    _state.update { it.copy(
                        isLoading = false,
                        nowPlayingMoviesUi = nowPlayingMovies.map { movie -> movie.toMovieUi() }
                    ) }
                }
                .onError { error ->
                    println(error.toUiText())
                }
        }
    }

    fun onAction(action: MovieListAction) {
        when (action) {
            else -> {}
        }
    }
}
