package org.ernisernis.ratemoviescmp.movie.data.mappers

import org.ernisernis.ratemoviescmp.movie.data.database.MovieEntity
import org.ernisernis.ratemoviescmp.movie.data.dto.MovieDto
import org.ernisernis.ratemoviescmp.movie.domain.Movie

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
    )
}

fun Movie.toBookEntity(): MovieEntity {
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
    )
}