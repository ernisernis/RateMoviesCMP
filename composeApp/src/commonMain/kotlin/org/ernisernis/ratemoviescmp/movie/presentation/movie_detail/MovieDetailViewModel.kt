package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ernisernis.ratemoviescmp.app.Route
import org.ernisernis.ratemoviescmp.core.domain.util.onSuccess
import org.ernisernis.ratemoviescmp.core.presentation.OnetimeWhileSubscribed
import org.ernisernis.ratemoviescmp.movie.data.mappers.toRatingUi
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieDetailUi
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieUi

class MovieDetailViewModel(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val movieId = savedStateHandle.toRoute<Route.MovieDetail>().id

    private val _state = MutableStateFlow(MovieDetailState())
    val state = _state
        .onStart {
            getMovieRating()
            observeBookmarkStatus()
        }
        .stateIn(
            scope = viewModelScope,
            started = OnetimeWhileSubscribed(5_000),
            initialValue = MovieDetailState()
        )

    fun onAction(action: MovieDetailAction) {
        when (action) {
            is MovieDetailAction.OnSelectedMovieChange -> {
                _state.update { it.copy(
                    movie = action.movie,
                    movieUi = action.movie.toMovieUi(),
                ) }
                viewModelScope.launch {
                    movieRepository
                        .getMovieDetail(id = movieId)
                        .onSuccess { movieDetail ->
                            _state.update { it.copy(
                                movie = state.value.movie?.copy(
                                    movieDetail = movieDetail
                                ),
                                movieUi = state.value.movieUi?.copy(
                                    movieDetailUi = movieDetail.toMovieDetailUi()
                                )
                            ) }
                        }
                }
            }
            is MovieDetailAction.OnBookmarkClick -> {
                if (state.value.movie?.movieDetail == null) return

                viewModelScope.launch {
                    if (state.value.isBookmarked) {
                        movieRepository.deleteFromBookmark(movieId)
                    } else {
                        state.value.movie?.let { movie ->
                            movieRepository.markAsBookmarked(movie)
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

    private fun getMovieRating() {
        viewModelScope.launch {
           movieRepository
               .getRating(movieId)
               .onSuccess { rating ->
                   _state.update { it.copy(
                       ratingUi = rating.toRatingUi()
                   ) }
               }
        }
    }
}
