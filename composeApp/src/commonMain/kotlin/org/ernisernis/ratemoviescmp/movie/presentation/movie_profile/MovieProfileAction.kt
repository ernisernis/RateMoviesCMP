package org.ernisernis.ratemoviescmp.movie.presentation.movie_profile

sealed interface MovieProfileAction {
    data object OnTestClick: MovieProfileAction
    data class OnMovieClick(val id: Int): MovieProfileAction
}