package org.ernisernis.ratemoviescmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.core.presentation.RateMoviesTheme
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieUi
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListScreen
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListState
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.components.MovieListItem


@Preview
@Composable
fun MovieListScreenPreview() {
    RateMoviesTheme(darkTheme = true, dynamicColor = false) {
        val state = MovieListState(
            nowPlayingMoviesUi = movieList.map { movie ->
               movie.toMovieUi()
            }
        )
        MovieListScreen(
            state = state,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            onAction = {}
        )
    }
}

@Preview
@Composable
fun MovieListItemPreview() {
    RateMoviesTheme(darkTheme = true, dynamicColor = false) {
        MovieListItem(
            movieUi = movie.toMovieUi(),
            modifier = Modifier
                .background(Color.White)
                .padding(24.dp)
                .height(Dimens.MovieListItemHeight)
                .width(Dimens.MovieListItemWidth)
            ,
            onClick = {}
        )
    }
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

val movieList: List<Movie> = (1..20).map {
    movie
}