package org.ernisernis.ratemoviescmp.movie.presentation.models

import org.ernisernis.ratemoviescmp.movie.domain.MovieDetail


data class MovieDetailUi(
    val id: Int,
    val releaseDate: String,
    val runtime: DisplayableValue,
    val voteAverage: String,
    val voteCount: String,
    val genres: List<MovieGenreUi>,
    val overview: String,
    val cast: List<CastUi>?,
    val crew: List<CrewUi>,
    val director: String?,
    val writer: String?,
)

data class DisplayableValue(
    val value: Int,
    val formatted: String,
)

fun MovieDetail.toMovieDetailUi(): MovieDetailUi {
    return MovieDetailUi(
        id = id,
        releaseDate = releaseDate,
        runtime = runtime.toDisplayableRuntime(),
        voteAverage = voteAverage.toString(),
        voteCount = voteCount.toString(),
        genres = genres.map { it.toMovieGenreUi() },
        overview = overview,
        cast = credits.cast.map { it.toCastUi() }.filter { it.profilePath != null }.ifEmpty { null },
        crew = credits.crew.map { it.toCrewUi() },
        director = credits.crew.find { it.job == "Director" }?.name,
        writer = credits.crew.find { it.job == "Screenplay" }?.name,
    )
}

fun Int.toDisplayableRuntime(): DisplayableValue {
    val hours = this / 60
    val mins = this % 60
    val formatted =
        if (hours > 0) {
            "${hours}h ${mins}min"
        } else {
            "${mins}min"
        }
    return DisplayableValue(
        value = this,
        formatted = formatted,
    )
}