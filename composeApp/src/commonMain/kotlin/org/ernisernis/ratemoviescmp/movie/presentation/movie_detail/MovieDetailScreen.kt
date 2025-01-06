package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.ernisernis.ratemoviescmp.core.presentation.components.PosterImage
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.movie.presentation.util.bottomInnerShadow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.banner1280wpreview
import ratemoviescmp.composeapp.generated.resources.description_movie_banner


@Composable
fun MovieDetailScreenRoot(
    viewModel: MovieDetailViewModel = koinViewModel(),
    onRateClick: (Int, String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MovieDetailScreen(
        state = state,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        onAction = { action ->
            when (action) {
                is MovieDetailAction.OnRateClick -> onRateClick(action.id, action.firebaseId)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )


}

@Composable
fun MovieDetailScreen(
    state: MovieDetailState,
    modifier: Modifier = Modifier,
    onAction: (MovieDetailAction) -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        // Banner
        AsyncImage(
            model = state.bannerUrl,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .bottomInnerShadow(),
            contentDescription = stringResource(Res.string.description_movie_banner),
            error = painterResource(Res.drawable.banner1280wpreview),
            placeholder = painterResource(Res.drawable.banner1280wpreview),
            fallback = painterResource(Res.drawable.banner1280wpreview),
        )

        Row(
            modifier = Modifier
                .padding(horizontal = Dimens.MovieDetailContainerPadding)
                .offset(y = -(45).dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Title
            Text(
                text = state.title,
                modifier = Modifier
                    .weight(1f),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
            // Image
            PosterImage(
                modifier = Modifier
                    .width(60.dp),
                url = state.imageUrl,
            )
        }

    }
}