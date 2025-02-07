package org.ernisernis.ratemoviescmp.movie.presentation.movie_profile

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.movie.data.mappers.toBookmarkMovieUi
import org.ernisernis.ratemoviescmp.movie.data.mappers.toRatingUi
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark.MovieBookmarkAction
import org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark.components.MovieBookmarkListItem
import org.ernisernis.ratemoviescmp.movie.presentation.movie_profile.components.MovieProfileListItem
import org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.MovieRateAction
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.bookmarks


@Composable
fun MovieProfileScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: MovieProfileViewModel = koinViewModel(),
    onMovieIdClick: (Int) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MovieProfileScreen(
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize()
            .horizontalScroll(rememberScrollState()),
        state = state,
        onAction = { action ->
            when (action)  {
                is MovieProfileAction.OnTestClick -> {}
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun MovieProfileScreen(
    modifier: Modifier = Modifier,
    state: MovieProfileState,
    onAction: (MovieProfileAction) -> Unit,
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
            modifier = modifier
                .padding(vertical = Dimens.MovieBookmarkItemPaddingSmall),
        ) {
            items(
                items = state.rateMovies,
                key = { it.id }
            ) { rating ->
                MovieProfileListItem(
                    modifier = Modifier
                        .animateItem()
                        .height(Dimens.MovieBookmarkItemHeight),
                    ratingUi = rating.toRatingUi(),
                    onClick = {
                        onAction(MovieProfileAction.OnMovieClick(rating.id))
                    },
                )
            }
        }
    }
}