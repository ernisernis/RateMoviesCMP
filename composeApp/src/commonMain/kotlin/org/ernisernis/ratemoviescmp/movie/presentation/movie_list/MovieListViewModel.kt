package org.ernisernis.ratemoviescmp.movie.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ernisernis.ratemoviescmp.core.domain.util.onError
import org.ernisernis.ratemoviescmp.core.domain.util.onSuccess
import org.ernisernis.ratemoviescmp.core.presentation.OnetimeWhileSubscribed
import org.ernisernis.ratemoviescmp.core.presentation.toUiText
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository

class MovieListViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state = _state
        .onStart { initData() }
        .stateIn(
            scope = viewModelScope,
            started = OnetimeWhileSubscribed(5_000),
            initialValue = MovieListState()
        )

    private fun initData() {
        viewModelScope.launch {
            movieRepository
                .getNowPlayingMovies()
                .onSuccess { nowPlayingMovies ->
                    _state.update { it.copy(
                        isLoading = false,
                        nowPlayingMovies = nowPlayingMovies
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(
                        isLoading = false,
                        errorMessage = error.toUiText(),
                    ) }
                }
        }
    }

    fun onAction(action: MovieListAction) {
        when (action) {
            else -> {}
        }
    }
}
