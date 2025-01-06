package org.ernisernis.ratemoviescmp.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.ernisernis.ratemoviescmp.core.presentation.RateMoviesTheme
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailScreenRoot
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListScreenRoot
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListViewModel
import org.koin.compose.viewmodel.koinViewModel

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
                    val viewModel = koinViewModel<MovieListViewModel>()
                    MovieListScreenRoot(
                        viewModel = viewModel,
                        onMovieClick = { movieUi ->
                            navController.navigate(
                                Route.MovieDetail(
                                    id = movieUi.id,
                                    bannerUrl = movieUi.banner,
                                    title = movieUi.title,
                                    imageUrl = movieUi.imageUrl,
                                )
                            )
                        }
                    )
                }

                composable<Route.MovieDetail> { entry ->
                    val args = entry.toRoute<Route.MovieDetail>()
                    val viewModel = koinViewModel<MovieDetailViewModel>()

                    LaunchedEffect(args) {
                        viewModel.initData(
                            id = args.id,
                            bannerUrl = args.bannerUrl,
                            title = args.title,
                            imageUrl = args.imageUrl
                        )
                    }

                    MovieDetailScreenRoot(
                        viewModel = viewModel,
                        onRateClick = { id, firebaseId ->
                            println(id)
                            println(firebaseId)
                        }
                    )
                }
            }
        }
    }
}