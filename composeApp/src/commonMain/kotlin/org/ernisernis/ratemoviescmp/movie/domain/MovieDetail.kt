package org.ernisernis.ratemoviescmp.movie.domain

data class MovieDetail(
    val id: Int,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: List<MovieGenre>,
    val overview: String,
    val credits: CreditsResponse
)