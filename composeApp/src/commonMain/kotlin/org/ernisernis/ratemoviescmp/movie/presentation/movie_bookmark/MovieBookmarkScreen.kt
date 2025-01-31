package org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
import org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark.components.MovieBookmarkListItem
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.bookmarks


@Composable
fun MovieBookmarkScreenRoot(
    viewModel: MovieBookmarkViewModel = koinViewModel(),
    onMovieClick: (Movie) -> Unit,
) {
   val state by viewModel.state.collectAsStateWithLifecycle()

    MovieBookmarkScreen(
        state = state,
        modifier = Modifier
            .fillMaxSize(),
        onAction = { action ->
            when (action)   {
                is MovieBookmarkAction.OnMovieClick -> onMovieClick(action.movie)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun MovieBookmarkScreen(
    state: MovieBookmarkState,
    modifier: Modifier = Modifier,
    onAction: (MovieBookmarkAction) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        // Title
        Text(
            text = stringResource(Res.string.bookmarks),
            modifier = Modifier
                .padding(Dimens.MovieListContainerPadding),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium,
        )

        // Bookmark items
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(400.dp),
            verticalItemSpacing = Dimens.MovieBookmarkItemPaddingSmall,
            horizontalArrangement = Arrangement.spacedBy(Dimens.MovieBookmarkItemPaddingSmall),
            modifier = modifier,
        ) {
            items(state.movies) { movie ->
               MovieBookmarkListItem(
                   modifier = Modifier
                       .height(Dimens.MovieBookmarkItemHeight),
                   movieUi = movie.toMovieUi(),
                   onClick = {}
               )
            }
        }
    }
}