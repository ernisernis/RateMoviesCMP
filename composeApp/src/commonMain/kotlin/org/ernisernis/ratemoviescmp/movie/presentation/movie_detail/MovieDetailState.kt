package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieDetailUi
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi

data class MovieDetailState(
    val movieUi: MovieUi? = null,
    val movieDetailUi: MovieDetailUi? = null,
)
