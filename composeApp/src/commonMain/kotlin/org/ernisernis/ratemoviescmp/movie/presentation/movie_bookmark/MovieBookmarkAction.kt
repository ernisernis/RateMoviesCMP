package org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark

import org.ernisernis.ratemoviescmp.movie.domain.Movie

sealed interface MovieBookmarkAction {
    data class OnMovieClick(val movie: Movie): MovieBookmarkAction
    data class OnBookmarkClick(val id: Int): MovieBookmarkAction
}