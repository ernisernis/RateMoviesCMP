package org.ernisernis.ratemoviescmp.movie.presentation.models

import org.ernisernis.ratemoviescmp.movie.domain.Cast

data class CastUi(
    val id: Int,
    val name: String,
    val profilePath: String,
    val character: String,
)

fun Cast.toCastUi(): CastUi {
    return CastUi(
        id = id,
        name = name,
        profilePath = "https://image.tmdb.org/t/p/h632$profilePath",
        character = character,
    )
}
