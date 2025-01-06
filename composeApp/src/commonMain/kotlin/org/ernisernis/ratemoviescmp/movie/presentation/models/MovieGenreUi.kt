package org.ernisernis.ratemoviescmp.movie.presentation.models

import org.ernisernis.ratemoviescmp.movie.domain.MovieGenre

data class MovieGenreUi(
    val id: Int,
    val name: String,
)

fun MovieGenre.toMovieGenreUi(): MovieGenreUi {
    return MovieGenreUi(
        id = id,
        name = name,
    )
}