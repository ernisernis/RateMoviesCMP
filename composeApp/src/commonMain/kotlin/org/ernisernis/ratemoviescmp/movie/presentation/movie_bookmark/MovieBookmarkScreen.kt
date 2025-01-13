package org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.bookmarks


@Composable
fun MovieBookmarkScreenRoot(
    viewModel: MovieBookmarkViewModel = koinViewModel(),
) {
   val state by viewModel.state.collectAsStateWithLifecycle()

    MovieBookmarkScreen(
        state = state,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
fun MovieBookmarkScreen(
    state: MovieBookmarkState,
    modifier: Modifier = Modifier,
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
    }
}