package org.ernisernis.ratemoviescmp.movie.data.mappers

import org.ernisernis.ratemoviescmp.core.presentation.getImageUrl
import org.ernisernis.ratemoviescmp.core.presentation.getReleaseYear
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
        imageUrl = posterPath.getImageUrl(),
        title = title,
        releaseYear = releaseDate.getReleaseYear(),
        runtimeFormatted = runtimeFormatted,
        voteAverage = voteAverage
    )
}