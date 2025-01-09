package org.ernisernis.ratemoviescmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ernisernis.ratemoviescmp.core.presentation.components.PosterImage
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.core.presentation.RateMoviesTheme
import org.ernisernis.ratemoviescmp.core.presentation.components.DefaultIconContainer
import org.ernisernis.ratemoviescmp.movie.domain.Cast
import org.ernisernis.ratemoviescmp.movie.domain.Crew
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.domain.MovieDetail
import org.ernisernis.ratemoviescmp.movie.domain.MovieGenre
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieDetailsUi
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieUi
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailScreen
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailState
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListScreen
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListState
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.components.MovieListItem
import org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.MovieRateScreen
import org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.MovieRateState


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

@Preview
@Composable
fun MovieDetailScreenPreview() {
    val movieUi = movie.toMovieUi()
    val state = MovieDetailState(
        movieUi = movieUi,
        movieDetailUi = defaultMovieDetails.toMovieDetailsUi()
    )

    RateMoviesTheme(darkTheme = true, dynamicColor = false) {
        MovieDetailScreen(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            onAction = {}
        )
    }
}

@Preview
@Composable
fun PosterImagePreview() {
    RateMoviesTheme(darkTheme = true, dynamicColor = false) {
        PosterImage(
            modifier = Modifier
                .width(160.dp),
            url = movie.toMovieUi().imageUrl,
        )
    }
}

@Preview
@Composable
fun MovieRateScreenPreview() {
    RateMoviesTheme(darkTheme = true, dynamicColor = false) {
        MovieRateScreen(
            state = MovieRateState(),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            onAction = {}
        )
    }
}


@Preview
@Composable
fun DefaultIconContainerPreview() {
    RateMoviesTheme(darkTheme = true, dynamicColor = false) {
        DefaultIconContainer(
            icon = Icons.Default.ArrowBackIosNew,
            onClick = {}
        )
    }
}

internal val movie = Movie(
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

internal val movieList: List<Movie> = (1..20).map {
    movie
}

internal val defaultMovieDetails =
    MovieDetail(
        id = 912649,
        releaseDate = "2024-10-22",
        runtime = 109,
        voteAverage = 6.387,
        voteCount = 800,
        genres =
        listOf(
            MovieGenre(
                id = 878,
                name = "Science Fiction",
            ),
            MovieGenre(
                id = 28,
                name = "Action",
            ),
            MovieGenre(
                id = 12,
                name = "Adventure",
            ),
        ),
        overview =
        "Eddie and Venom are on the run. Hunted by both of their worlds and with the net closing in, " +
                "the duo are forced into a devastating decision that will bring the curtains down on Venom and Eddie's last dance",
        cast =
        listOf(
            Cast(
                id = 2524,
                name = "Tom Hardy",
                profilePath = "/d81K0RH8UX7tZj49tZaQhZ9ewH.jpg",
                character = "Eddie Brock / Venom",
            ),
            Cast(
                id = 2524,
                name = "Tom Hardy",
                profilePath = "/d81K0RH8UX7tZj49tZaQhZ9ewH.jpg",
                character = "Eddie Brock / Venom",
            ),
        ),
        crew =
        listOf(
            Crew(
                id = 1195200,
                name = "David Michelinie",
                job = "Characters",
                profilePath = "/bSpGO1dKFfwb9mUc4KdUpBpRfYH.jpg",
            ),
            Crew(
                id = 1195200,
                name = "David Michelinie",
                job = "Director",
                profilePath = "/bSpGO1dKFfwb9mUc4KdUpBpRfYH.jpg",
            ),
        ),
    )