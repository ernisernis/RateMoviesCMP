package org.ernisernis.ratemoviescmp.movie.data.mappers

import org.ernisernis.ratemoviescmp.movie.data.dto.CastDto
import org.ernisernis.ratemoviescmp.movie.data.dto.CrewDto
import org.ernisernis.ratemoviescmp.movie.data.dto.MovieDetailDto
import org.ernisernis.ratemoviescmp.movie.data.dto.MovieGenreDto
import org.ernisernis.ratemoviescmp.movie.domain.Cast
import org.ernisernis.ratemoviescmp.movie.domain.Crew
import org.ernisernis.ratemoviescmp.movie.domain.MovieDetail
import org.ernisernis.ratemoviescmp.movie.domain.MovieGenre


fun MovieDetailDto.toMovieDetails(): MovieDetail {
    return MovieDetail(
        id = id,
        releaseDate = releaseDate,
        runtime = runtime,
        voteAverage = voteAverage,
        voteCount = voteCount,
        genres = genres.map { it.toMovieGenre() },
        overview = overview,
        cast = credits.cast.map { it.toCast() }.filter { it.profilePath != null },
        crew = credits.crew.map { it.toCrew() }
    )
}

fun MovieGenreDto.toMovieGenre(): MovieGenre {
    return MovieGenre(
        id = id,
        name = name
    )
}

fun CastDto.toCast(): Cast {
    return Cast(
        id = id,
        name = name,
        profilePath = profilePath,
        character = character,
    )
}

fun CrewDto.toCrew(): Crew {
    return Crew(
        id = id,
        name = name,
        job = job,
        profilePath = profilePath ?: "",
    )
}
