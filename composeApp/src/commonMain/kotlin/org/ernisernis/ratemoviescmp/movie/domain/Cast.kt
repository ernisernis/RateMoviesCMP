package org.ernisernis.ratemoviescmp.movie.domain

data class Cast(
    val id: Int,
    val name: String,
    val profilePath: String?,
    val character: String,
)
