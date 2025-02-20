package org.ernisernis.ratemoviescmp.movie.presentation.movie_rate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ernisernis.ratemoviescmp.core.domain.util.onError
import org.ernisernis.ratemoviescmp.core.domain.util.onSuccess
import org.ernisernis.ratemoviescmp.core.presentation.OnetimeWhileSubscribed
import org.ernisernis.ratemoviescmp.movie.data.mappers.toRating
import org.ernisernis.ratemoviescmp.movie.data.mappers.toRatingUi
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieUi
import org.ernisernis.ratemoviescmp.movie.presentation.models.use_case.ValidateRateDescription
import org.ernisernis.ratemoviescmp.movie.presentation.models.use_case.ValidateRateNumber

class MovieRateViewModel(
    private val movieRepository: MovieRepository,
    private val validateRateDescription: ValidateRateDescription,
    private val validateRateNumber: ValidateRateNumber,
): ViewModel() {

    private val _state = MutableStateFlow(MovieRateState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieRateState()
        )

    fun onAction(action: MovieRateAction) {
       when (action) {
           is MovieRateAction.OnMovieRateClick -> {
               _state.update {
                   it.copy(
                       selectedIndex = action.index,
                       indexError = null,
                   )
               }
           }
           is MovieRateAction.OnMovieRateSubmit -> submitRate(action.movie)
           is MovieRateAction.OnSelectedMovieChange -> {
               getMovieRating(action.movie.id)
               _state.update { it.copy(
                   movie = action.movie,
                   movieUi = action.movie.toMovieUi()
               ) }
           }
           is MovieRateAction.OnDescriptionChange -> {
               _state.update { it.copy(
                   description = action.text
               ) }
           }
           else -> Unit
       }
    }

    private fun submitRate(movie: Movie) {
        val descriptionResult = validateRateDescription.execute(state.value.description)
        val numberResult = validateRateNumber.execute(state.value.selectedIndex)

        val hasError = listOf(
            descriptionResult,
            numberResult,
        ).any { !it.successful }

        if (hasError) {
            _state.update {
                it.copy(
                    descriptionError = descriptionResult.errorMessage,
                    indexError = numberResult.errorMessage,
                )
            }
        } else {
            viewModelScope.launch {
                val rating = movie.toRating().copy(
                    description = state.value.description.trim().ifEmpty { null },
                    userRating = state.value.selectedIndex,
                )
                movieRepository
                    .rateMovie(
                        movie = movie,
                        rating = rating,
                    )
                    .onSuccess {
                        _state.update { it.copy(
                            popBackStackFlag = true
                        ) }
                    }
                    .onError {
                        // TODO: handle error
                    }
            }
        }
    }

    private fun getMovieRating(movieId: Int) {
        viewModelScope.launch {
            movieRepository
                .getRating(movieId)
                .onSuccess { rating ->
                    val ratingUi = rating.toRatingUi()
                    _state.update { it.copy(
                        description = ratingUi.description ?: "",
                        selectedIndex = ratingUi.userRating,
                    ) }
                }
        }
    }
}