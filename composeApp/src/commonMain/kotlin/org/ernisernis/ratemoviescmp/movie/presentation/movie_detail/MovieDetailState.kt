package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.domain.MovieDetail
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieDetailUi
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi

data class MovieDetailState(
    val movie: Movie? = null,
    val movieUi: MovieUi? = null,
    val movieDetail: MovieDetail? = null,
    val movieDetailUi: MovieDetailUi? = null,
    val isBookmarked: Boolean = false,
)
