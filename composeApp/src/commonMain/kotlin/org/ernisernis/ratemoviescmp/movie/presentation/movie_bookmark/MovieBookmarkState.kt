package org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark

import org.ernisernis.ratemoviescmp.movie.domain.Movie

data class MovieBookmarkState(
    val movies: List<Movie> = listOf(),
)
