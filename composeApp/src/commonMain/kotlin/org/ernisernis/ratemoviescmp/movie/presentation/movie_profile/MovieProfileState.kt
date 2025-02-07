package org.ernisernis.ratemoviescmp.movie.presentation.movie_profile

import org.ernisernis.ratemoviescmp.movie.domain.Rating


data class MovieProfileState(
    val rateMovies: List<Rating> = listOf()
)