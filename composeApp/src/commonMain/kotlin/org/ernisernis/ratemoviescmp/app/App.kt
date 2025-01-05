package org.ernisernis.ratemoviescmp.app

import androidx.compose.runtime.Composable
import org.ernisernis.ratemoviescmp.core.presentation.RateMoviesTheme
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListScreenRoot

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    RateMoviesTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        MovieListScreenRoot()
    }
}