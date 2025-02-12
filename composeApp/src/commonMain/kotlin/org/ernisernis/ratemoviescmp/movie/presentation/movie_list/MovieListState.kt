package org.ernisernis.ratemoviescmp.movie.presentation.movie_list

import org.ernisernis.ratemoviescmp.core.presentation.UiText
import org.ernisernis.ratemoviescmp.movie.domain.Movie

data class MovieListState(
    val isLoading: Boolean = true,
    val nowPlayingMovies: List<Movie> = emptyList(),
    val errorMessage: UiText? = null,
)