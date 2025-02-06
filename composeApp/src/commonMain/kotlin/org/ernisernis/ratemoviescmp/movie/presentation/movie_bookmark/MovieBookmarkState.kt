package org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark

import org.ernisernis.ratemoviescmp.movie.domain.BookmarkMovie

data class MovieBookmarkState(
    val bookmarkMovies: List<BookmarkMovie> = listOf(),
)
