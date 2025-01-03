package org.ernisernis.ratemoviescmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieUi
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.components.MovieListItem


@Preview
@Composable
fun MovieListItemPreview() {
    MovieListItem(
        movieUi = movie.toMovieUi(),
        modifier = Modifier,
        onClick = {}
    )
}

val movie = Movie(
    id = 0,
    title = "Terrifier 3",
    adult = false,
    backdropPath = "",
    genreIds = listOf(1,3,4),
    originalLanguage = "ENG",
    originalTitle = "Terrifier 3",
    overview = "Five years after surviving Art the Clown's Halloween massacre",
    popularity = 3456.34,
    posterPath = "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
    releaseDate = "2024.12.12",
    video = false,
    voteAverage = 123.3,
    voteCount = 12345
)