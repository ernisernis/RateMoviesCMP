package org.ernisernis.ratemoviescmp.movie.presentation.models

data class BookmarkMovieUi(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val releaseYear: String,
    val runtimeFormatted: String?,
    val voteAverage: String,
)
