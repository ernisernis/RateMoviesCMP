package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ernisernis.ratemoviescmp.app.Route
import org.ernisernis.ratemoviescmp.core.domain.util.onSuccess
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieDetailUi
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieUi

class MovieDetailViewModel(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val movieId = savedStateHandle.toRoute<Route.MovieDetail>().id

    private val _state = MutableStateFlow(MovieDetailState())
    val state = _state.asStateFlow()

    init {
        observeBookmarkStatus()
    }

    fun onAction(action: MovieDetailAction) {
        when (action) {
            is MovieDetailAction.OnSelectedMovieChange -> {
                _state.update { it.copy(
                    movie = action.movie,
                    movieUi = action.movie.toMovieUi(),
                ) }
                viewModelScope.launch {
                    movieRepository
                        .getMovieDetail(id = action.movie.id)
                        .onSuccess { movieDetail ->
                            _state.update { it.copy(
                                movieDetailUi = movieDetail.toMovieDetailUi(),
                                movieDetail = movieDetail,
                            ) }
                        }
                }
            }
            is MovieDetailAction.OnBookmarkClick -> {
                viewModelScope.launch {
                    if (state.value.isBookmarked) {
                        movieRepository.deleteFromBookmark(movieId)
                    } else {
                        if (state.value.movie != null && state.value.movieDetail != null) {
                            movieRepository.markAsBookmarked(state.value.movie!!, state.value.movieDetail!!)
                        }
                    }
                }
            }
            else -> Unit
        }
    }

    private fun observeBookmarkStatus() {
        movieRepository
            .isBookBookmarked(movieId)
            .onEach { isBookmarked ->
                _state.update { it.copy(
                    isBookmarked = isBookmarked
                ) }
            }
            .launchIn(viewModelScope)
    }
}
