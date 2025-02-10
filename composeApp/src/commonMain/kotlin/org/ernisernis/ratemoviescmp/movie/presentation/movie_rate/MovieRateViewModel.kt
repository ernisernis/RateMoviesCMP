package org.ernisernis.ratemoviescmp.movie.presentation.movie_rate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ernisernis.ratemoviescmp.core.domain.util.onError
import org.ernisernis.ratemoviescmp.core.domain.util.onSuccess
import org.ernisernis.ratemoviescmp.movie.data.mappers.toRating
import org.ernisernis.ratemoviescmp.movie.data.mappers.toRatingUi
import org.ernisernis.ratemoviescmp.movie.domain.MovieRepository
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieUi

class MovieRateViewModel(
    private val movieRepository: MovieRepository,
): ViewModel() {

    private val _state = MutableStateFlow(MovieRateState())
    val state = _state.asStateFlow()

    fun onAction(action: MovieRateAction) {
       when (action) {
           is MovieRateAction.OnMovieRateClick -> {
               _state.update {
                   it.copy(
                       selectedIndex = action.index,
                   )
               }
           }
           is MovieRateAction.OnMovieRateSubmit -> {
               viewModelScope.launch {
                   val rating = action.movie.toRating().copy(
                       description = state.value.description,
                       userRating = state.value.selectedIndex,
                   )
                   movieRepository
                       .rateMovie(
                           movie = action.movie,
                           rating = rating
                       )
                       .onSuccess {
                           // TODO: Exit the rating screen
                       }
                       .onError {
                           // TODO: handle error
                       }
               }
           }
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