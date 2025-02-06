package org.ernisernis.ratemoviescmp.movie.presentation.movie_rate

import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi


data class MovieRateState(
    val movie: Movie? = null,
    val movieUi: MovieUi? = null,
    val selectedIndex: Int = 0,
    val description: String = "",
)


