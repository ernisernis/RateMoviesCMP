package org.ernisernis.ratemoviescmp.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.ernisernis.ratemoviescmp.core.presentation.RateMoviesTheme
import org.ernisernis.ratemoviescmp.movie.presentation.SelectedMovieViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailAction
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailScreenRoot
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListScreenRoot
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.MovieRateAction
import org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.RateDetailScreenRoot
import org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.MovieRateViewModel
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
                    val selectedMovieViewModel = it.sharedKoinViewModel<SelectedMovieViewModel>(navController)

                    LaunchedEffect(true) {
                        selectedMovieViewModel.onSelectMovie(null)
                    }

                    MovieListScreenRoot(
                        viewModel = viewModel,
                        onMovieClick = { movieUi ->
                            selectedMovieViewModel.onSelectMovie(movieUi)
                            navController.navigate(Route.MovieDetail)
                        }
                    )
                }

                composable<Route.MovieDetail> {
                    val viewModel = koinViewModel<MovieDetailViewModel>()
                    val selectedMovieViewModel = it.sharedKoinViewModel<SelectedMovieViewModel>(navController)
                    val selectedMovie by selectedMovieViewModel.selectedMovie.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedMovie) {
                        selectedMovie?.let { movieUi ->
                            viewModel.onAction(MovieDetailAction.OnSelectedMovieChange(movieUi))
                        }
                    }

                    MovieDetailScreenRoot(
                        viewModel = viewModel,
                        onRateClick = { navController.navigate(Route.MovieRate) }
                    )
                }

                composable<Route.MovieRate> {
                    val viewModel = koinViewModel<MovieRateViewModel>()
                    val selectedMovieViewModel = it.sharedKoinViewModel<SelectedMovieViewModel>(navController)
                    val selectedMovie by selectedMovieViewModel.selectedMovie.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedMovie) {
                        selectedMovie?.let { movieUi ->
                            viewModel.onAction(MovieRateAction.OnSelectedMovieChange(movieUi))
                        }
                    }

                    RateDetailScreenRoot(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}