package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

sealed interface MovieDetailAction {
    data class OnRateClick(val id: Int, val bannerUrl: String, val title: String, val imageUrl: String): MovieDetailAction
}