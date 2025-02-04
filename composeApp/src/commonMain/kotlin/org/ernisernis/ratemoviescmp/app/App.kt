package org.ernisernis.ratemoviescmp.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.serialization.generateHashCode
import org.ernisernis.ratemoviescmp.core.presentation.BottomNavigationVisibility
import org.ernisernis.ratemoviescmp.core.presentation.RateMoviesTheme
import org.ernisernis.ratemoviescmp.movie.presentation.SelectedMovieViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark.MovieBookmarkScreenRoot
import org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark.MovieBookmarkViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailAction
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailScreenRoot
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.MovieDetailViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListScreenRoot
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.MovieListViewModel
import org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.MovieRateAction
import org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.MovieRateScreenRoot
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
        var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

        val bottomBarState = rememberSaveable {
            mutableStateOf(true)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                Route.MovieList.serializer().generateHashCode() -> {
                    selectedIndex = 0
                    bottomBarState.value = true
                }
                Route.MovieBookmark.serializer().generateHashCode() -> {
                    selectedIndex = 1
                    bottomBarState.value = true
                }
                else -> {
                    bottomBarState.value = false
                }
            }
        }

        Scaffold(
            bottomBar = {
                BottomNavigationVisibility(
                    visible = bottomBarState.value,
                ) {
                    BottomNavigation(
                        modifier = Modifier
                            .navigationBarsPadding(),
                        backgroundColor = MaterialTheme.colorScheme.surface,
                    ) {
                        topLevelRoutes.forEachIndexed { index, topLevelRoute ->
                            NavigationBarItem(
                                icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name) } ,
                                selected = index == selectedIndex,
                                onClick = {
                                    selectedIndex = index
                                    navController.navigate(topLevelRoute.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Color.White,
                                    unselectedIconColor = Color.DarkGray,
                                    indicatorColor = Color.Transparent
                                )
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background),
                startDestination = Route.MovieGraph
            ) {
                navigation<Route.MovieGraph>(
                    startDestination = Route.MovieList
                ) {
                    MovieApp(
                        navController = navController
                    )
                }
            }
        }

    }
}

fun NavGraphBuilder.MovieApp(
    navController: NavController,
) {
    composable<Route.MovieList> {
        val viewModel = koinViewModel<MovieListViewModel>()
        val selectedMovieViewModel = it.sharedKoinViewModel<SelectedMovieViewModel>(navController)

        LaunchedEffect(true) {
            selectedMovieViewModel.onSelectMovie(null)
        }

        MovieListScreenRoot(
            viewModel = viewModel,
            onMovieClick = { movie ->
                selectedMovieViewModel.onSelectMovie(movie)
                navController.navigate(Route.MovieDetail(movie.id))
            }
        )
    }

    composable<Route.MovieDetail> {
        val viewModel = koinViewModel<MovieDetailViewModel>()
        val selectedMovieViewModel = it.sharedKoinViewModel<SelectedMovieViewModel>(navController)
        val selectedMovie by selectedMovieViewModel.selectedMovie.collectAsStateWithLifecycle()

        LaunchedEffect(selectedMovie) {
            selectedMovie?.let { movie ->
                viewModel.onAction(MovieDetailAction.OnSelectedMovieChange(movie))
            }
        }

        MovieDetailScreenRoot(
            viewModel = viewModel,
            onRateClick = {
                navController.navigate(Route.MovieRate)
            },
            onBackClick = {
                navController.navigateUp()
            },
        )
    }

    composable<Route.MovieRate> {
        val viewModel = koinViewModel<MovieRateViewModel>()
        val selectedMovieViewModel = it.sharedKoinViewModel<SelectedMovieViewModel>(navController)
        val selectedMovie by selectedMovieViewModel.selectedMovie.collectAsStateWithLifecycle()

        LaunchedEffect(selectedMovie) {
            selectedMovie?.let { movie ->
                viewModel.onAction(MovieRateAction.OnSelectedMovieChange(movie))
            }
        }

        MovieRateScreenRoot(
            viewModel = viewModel,
            onBackClick = {
                navController.navigateUp()
            }
        )
    }

    composable<Route.MovieBookmark> {
        val viewModel = koinViewModel<MovieBookmarkViewModel>()
        val selectedMovieViewModel = it.sharedKoinViewModel<SelectedMovieViewModel>(navController)

        MovieBookmarkScreenRoot(
            viewModel = viewModel,
            onMovieClick = { movie ->
                selectedMovieViewModel.onSelectMovie(movie)
                navController.navigate(Route.MovieDetail(movie.id))
            }
        )
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