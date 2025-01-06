package org.ernisernis.ratemoviescmp.movie.presentation.movie_list

import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi

sealed interface MovieListAction {
    data class OnMovieClick(val movieUi: MovieUi): MovieListAction
}