package org.ernisernis.ratemoviescmp.movie.presentation.movie_list

import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi

data class MovieListState(
    val isLoading: Boolean = false,
    val nowPlayingMoviesUi: List<MovieUi> = emptyList()
)