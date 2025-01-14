package org.ernisernis.ratemoviescmp.movie.presentation.movie_rate

import org.ernisernis.ratemoviescmp.movie.domain.Movie

sealed interface MovieRateAction {
    data class OnMovieRateClick(val index: Int): MovieRateAction
    data object OnMovieRateSubmit: MovieRateAction
    data class OnSelectedMovieChange(val movie: Movie): MovieRateAction
    data object OnBackClick: MovieRateAction
}