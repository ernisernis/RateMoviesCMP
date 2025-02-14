package org.ernisernis.ratemoviescmp.movie.presentation.movie_profile

sealed interface MovieProfileAction {
    data class OnMovieClick(val id: Int): MovieProfileAction
    data class OnExtendRatingDescription(val id: Int, val extend: Boolean): MovieProfileAction
    data class OnShowTooltip(val id: Int, val show: Boolean): MovieProfileAction
    data class OnReviewDelete(val id: Int): MovieProfileAction
}