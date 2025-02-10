package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi
import org.ernisernis.ratemoviescmp.movie.presentation.models.RatingUi

data class MovieDetailState(
    val movie: Movie? = null,
    val movieUi: MovieUi? = null,
    val isBookmarked: Boolean = false,
    val ratingUi: RatingUi? = null,
)
