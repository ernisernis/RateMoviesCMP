package org.ernisernis.ratemoviescmp.movie.presentation.movie_list

import org.ernisernis.ratemoviescmp.core.presentation.UiText
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi

data class MovieListState(
    val isLoading: Boolean = true,
    val nowPlayingMoviesUi: List<MovieUi> = emptyList(),
    val errorMessage: UiText? = null,
)