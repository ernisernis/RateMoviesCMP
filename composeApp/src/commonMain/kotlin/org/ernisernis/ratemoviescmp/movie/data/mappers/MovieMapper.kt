package org.ernisernis.ratemoviescmp.movie.data.mappers

import kotlinx.datetime.Clock
import org.ernisernis.ratemoviescmp.core.presentation.formatVoteAverage
import org.ernisernis.ratemoviescmp.movie.data.database.entities.BookmarkEntity
import org.ernisernis.ratemoviescmp.movie.data.database.entities.MovieEntity
import org.ernisernis.ratemoviescmp.movie.data.dto.MovieDto
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.domain.Rating
import org.ernisernis.ratemoviescmp.movie.presentation.models.toDisplayableRuntime

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        adult = adult,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        movieDetail = null,
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        movieDetailDto = movieDetail?.toMovieDetailDto(),
    )
}

fun Movie.toBookmarkEntity(): BookmarkEntity {
    return BookmarkEntity(
        id = id,
        posterPath = posterPath,
        title = title,
        releaseDate = releaseDate,
        runtimeFormatted = movieDetail?.runtime?.toDisplayableRuntime()?.formatted,
        voteAverage = voteAverage.formatVoteAverage(),
        creationTime = Clock.System.now().toEpochMilliseconds()
    )
}

fun MovieEntity.toMovie(): Movie{
    return Movie(
        id = id,
        title = title,
        adult = adult,
        backdropPath = backdropPath?: "",
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        movieDetail = movieDetailDto?.toMovieDetail(),
    )
}

fun Movie.toRating(): Rating {
    return Rating(
        id = id,
        posterPath = posterPath,
        title = title,
        releaseDate = releaseDate,
        runtimeFormatted = movieDetail?.runtime?.toDisplayableRuntime()?.formatted,
        voteAverage = voteAverage.formatVoteAverage(),
        description = null,
        userRating = 0,
        creationTime = Clock.System.now().toEpochMilliseconds()
    )
}