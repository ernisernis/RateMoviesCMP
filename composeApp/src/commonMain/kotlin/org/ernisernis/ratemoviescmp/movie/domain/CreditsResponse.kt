package org.ernisernis.ratemoviescmp.movie.domain

data class CreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>
)