package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi

sealed interface MovieDetailAction {
    data object OnRateClick: MovieDetailAction
    data class OnSelectedMovieChange(val movieUi: MovieUi): MovieDetailAction
    data object OnBackClick: MovieDetailAction
}