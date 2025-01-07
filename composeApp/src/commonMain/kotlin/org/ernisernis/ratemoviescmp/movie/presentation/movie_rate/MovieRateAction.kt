package org.ernisernis.ratemoviescmp.movie.presentation.movie_rate

import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi

sealed interface MovieRateAction {
    data class OnMovieRateClick(val index: Int): MovieRateAction
    data object OnMovieRateSubmit: MovieRateAction
    data class OnSelectedMovieChange(val movieUi: MovieUi): MovieRateAction
}