package org.ernisernis.ratemoviescmp.movie.presentation.movie_rate

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.core.presentation.components.PosterImage
import org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.components.IconRate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.banner1280wpreview
import ratemoviescmp.composeapp.generated.resources.description_banner
import ratemoviescmp.composeapp.generated.resources.rate
import ratemoviescmp.composeapp.generated.resources.rate_title


@Composable
fun RateDetailScreenRoot(
    viewModel: MovieRateViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RateDetailScreen(
        state = state,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        onAction = viewModel::onAction
    )
}

@Composable
fun RateDetailScreen(
    modifier: Modifier = Modifier,
    state: MovieRateState,
    onAction: (MovieRateAction) -> Unit,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        // Banner
        AsyncImage(
            model = state.movieUi?.banner,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .blur(
                    radiusX = 15.dp,
                    radiusY = 15.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                ),
            contentDescription = stringResource(Res.string.description_banner),
            error = painterResource(Res.drawable.banner1280wpreview),
            placeholder = painterResource(Res.drawable.banner1280wpreview),
            fallback = painterResource(Res.drawable.banner1280wpreview),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(Dimens.RateDetailComponentPadding)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(Dimens.RateDetailItemPaddingBig),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            state.movieUi?.imageUrl?.let {
                PosterImage(
                    url = it,
                    modifier = Modifier
                        .width(152.dp)
                )
            }
            // Title
            Text(
                text = if (state.selectedIndex != 0) {
                    state.selectedIndex.toString()
                } else {
                    stringResource(Res.string.rate_title, state.movieUi?.title.toString())
                },
                modifier = Modifier
                    .padding(Dimens.RateDetailComponentPadding),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )
            // Star ratings
            Row {
                (1..10).map { i ->
                   IconRate(
                       icon = if (i <= state.selectedIndex) Icons.Default.StarRate else Icons.Default.StarOutline,
                       modifier = Modifier.size(32.dp),
                       onClick = {
                          onAction(MovieRateAction.OnMovieRateClick(i))
                       }
                   )
                }
            }
            // Button
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .clickable {
                        onAction(MovieRateAction.OnMovieRateSubmit)
                    }
                    .padding(Dimens.RateDetailComponentPadding)
                    .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(20)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(Res.string.rate),
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}