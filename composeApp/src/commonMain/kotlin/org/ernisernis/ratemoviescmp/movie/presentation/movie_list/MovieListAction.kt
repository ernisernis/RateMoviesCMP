package org.ernisernis.ratemoviescmp.movie.presentation.movie_list

import org.ernisernis.ratemoviescmp.movie.domain.Movie

sealed interface MovieListAction {
    data class OnMovieClick(val movie: Movie): MovieListAction
}