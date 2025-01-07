package org.ernisernis.ratemoviescmp.movie.presentation.movie_rate

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieRateViewModel(): ViewModel() {

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
           MovieRateAction.OnMovieRateSubmit -> {}
           is MovieRateAction.OnSelectedMovieChange -> {
               _state.update { it.copy(
                   movieUi = action.movieUi
               ) }
           }
       }
    }
}