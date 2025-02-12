package org.ernisernis.ratemoviescmp.movie.presentation.movie_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.presentation.models.toMovieUi
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.components.MovieListItem
import org.ernisernis.ratemoviescmp.movie.presentation.movie_list.components.MovieListLoading
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.movie_title_now_playing

@Composable
fun MovieListScreenRoot(
    viewModel: MovieListViewModel = koinViewModel(),
    onMovieClick: (Movie) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoading) {
        MovieListLoading(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
        )
    } else {
        MovieListScreen(
            state = state,
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize(),
            onAction = { action ->
                when (action)  {
                    is MovieListAction.OnMovieClick -> onMovieClick(action.movie)
                    else -> Unit
                }
                viewModel.onAction(action)
            }
        )
    }
}

@Composable
fun MovieListScreen(
    state: MovieListState,
    modifier: Modifier = Modifier,
    onAction: (MovieListAction) -> Unit,
) {
    Column(
        modifier = modifier
    ) {

        state.errorMessage?.let { message ->
            Text(
                text = message.asString(),
                modifier = Modifier
                    .padding(Dimens.MovieListContainerPadding),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium,
            )
        }

        // Title
        Text(
            text = stringResource(Res.string.movie_title_now_playing),
            modifier = Modifier
                .padding(Dimens.MovieListContainerPadding),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium,
        )

        // Movie items
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(Dimens.MovieListContainerPadding),
        ) {
            items(state.nowPlayingMovies) { movie ->
                MovieListItem(
                    movieUi = movie.toMovieUi(),
                    modifier = Modifier
                        .height(Dimens.MovieListItemHeight)
                        .width(Dimens.MovieListItemWidth),
                    onClick = {
                        onAction(MovieListAction.OnMovieClick(movie))
                    }
                )
            }
        }
    }
}