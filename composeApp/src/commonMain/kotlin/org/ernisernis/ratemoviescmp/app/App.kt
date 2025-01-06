package org.ernisernis.ratemoviescmp.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.MovieGraph
        ) {
            navigation<Route.MovieGraph>(
                startDestination = Route.MovieList
            ) {
                composable<Route.MovieList> {
                    MovieListScreenRoot(
                        onMovieClick = { movieUi ->
                            navController.navigate(
                                Route.MovieDetail(movieUi.id)
                            )
                        }
                    )
                }
                composable<Route.MovieDetail> { entry ->
                    val args = entry.toRoute<Route.MovieDetail>()

                    Box(Modifier.fillMaxSize()) {
                        Text("Movie Detail screen ${args.id}")
                    }
                }
            }
        }
    }
}