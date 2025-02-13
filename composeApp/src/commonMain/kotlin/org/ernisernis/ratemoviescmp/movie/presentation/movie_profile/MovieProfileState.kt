package org.ernisernis.ratemoviescmp.movie.presentation.movie_profile

import org.ernisernis.ratemoviescmp.movie.presentation.models.RatingUi


data class MovieProfileState(
    val ratingsUi: List<RatingUi> = listOf()
)