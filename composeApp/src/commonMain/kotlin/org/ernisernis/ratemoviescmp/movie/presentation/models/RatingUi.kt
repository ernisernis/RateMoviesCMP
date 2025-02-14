package org.ernisernis.ratemoviescmp.movie.presentation.models

data class RatingUi(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val releaseYear: String,
    val runtimeFormatted: String?,
    val voteAverage: String,
    val description: String?,
    val userRating: Int,
    val extended: Boolean,
    val tooltip: Boolean,
)
