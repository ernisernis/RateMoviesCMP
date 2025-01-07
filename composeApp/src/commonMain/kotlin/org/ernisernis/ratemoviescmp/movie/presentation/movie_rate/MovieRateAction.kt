package org.ernisernis.ratemoviescmp.movie.presentation.movie_rate

sealed interface MovieRateAction {
    data class OnMovieRateClick(val index: Int): MovieRateAction
    object OnMovieRateSubmit: MovieRateAction
}