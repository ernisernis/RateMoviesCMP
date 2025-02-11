package org.ernisernis.ratemoviescmp.movie.data.mappers

import org.ernisernis.ratemoviescmp.movie.data.database.entities.BookmarkEntity
import org.ernisernis.ratemoviescmp.movie.domain.BookmarkMovie
import org.ernisernis.ratemoviescmp.movie.presentation.models.BookmarkMovieUi


fun BookmarkEntity.toBookmarkMovie(): BookmarkMovie {
    return BookmarkMovie(
        id = id,
        posterPath = posterPath,
        title = title,
        releaseDate = releaseDate,
        runtimeFormatted = runtimeFormatted,
        voteAverage = voteAverage,
        creationTime = creationTime,
    )
}

fun BookmarkMovie.toBookmarkMovieUi(): BookmarkMovieUi {
    return BookmarkMovieUi(
        id = id,
        imageUrl = "https://image.tmdb.org/t/p/w780$posterPath",
        title = title,
        releaseYear = releaseDate.substringBefore("-"),
        runtimeFormatted = runtimeFormatted,
        voteAverage = voteAverage
    )
}