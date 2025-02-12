package org.ernisernis.ratemoviescmp.movie.presentation.models

import org.ernisernis.ratemoviescmp.core.presentation.formatVoteAverage
import org.ernisernis.ratemoviescmp.core.presentation.getBannerUrl
import org.ernisernis.ratemoviescmp.core.presentation.getImageUrl
import org.ernisernis.ratemoviescmp.core.presentation.getReleaseYear
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
        imageUrl = posterPath.getImageUrl(),
        releaseDate = releaseDate,
        genres = emptyList(),
        voteAverage = voteAverage.formatVoteAverage(),
        popularity = popularity.toInt().toString(),
        banner = backdropPath.getBannerUrl(),
        releaseYear = releaseDate.getReleaseYear(),
        movieDetailUi = movieDetail?.toMovieDetailUi(),
    )
}
