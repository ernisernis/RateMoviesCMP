package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieDetailUi

data class MovieDetailState(
    val id: Int = 0,
    val bannerUrl: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val movieDetailUi: MovieDetailUi? = null,
)
