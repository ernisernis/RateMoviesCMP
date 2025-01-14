package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

import org.ernisernis.ratemoviescmp.movie.domain.Movie

sealed interface MovieDetailAction {
    data object OnRateClick: MovieDetailAction
    data class OnSelectedMovieChange(val movie: Movie): MovieDetailAction
    data object OnBackClick: MovieDetailAction
    data object OnBookmarkClick: MovieDetailAction
}