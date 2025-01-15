package org.ernisernis.ratemoviescmp.movie.presentation.models

import org.ernisernis.ratemoviescmp.movie.domain.Movie

data class MovieUi(
    val id: Int,
    val title: String,
    val adult: Boolean,
    val overview: String,
    val imageUrl: String,
    val releaseDate: String,
    val genres: List<String>,
    val voteAverage: String,
    val popularity: String,
    val banner: String,
    val releaseYear: String,
    val movieDetailUi: MovieDetailUi?,
)

fun Movie.toMovieUi(): MovieUi {
    return MovieUi(
        id = id,
        title = title,
        adult = adult,
        overview = overview,
        imageUrl = "https://image.tmdb.org/t/p/w780$posterPath",
        releaseDate = releaseDate,
        genres = emptyList(),
        voteAverage = voteAverage.toString(),
        popularity = popularity.toInt().toString(),
        banner = "https://image.tmdb.org/t/p/w1280$backdropPath",
        releaseYear = releaseDate.substringBefore("-"),
        movieDetailUi = movieDetail?.toMovieDetailUi(),
    )
}