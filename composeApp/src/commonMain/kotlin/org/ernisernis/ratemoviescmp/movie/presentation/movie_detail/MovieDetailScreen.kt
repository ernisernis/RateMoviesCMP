package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.ernisernis.ratemoviescmp.movie.presentation.components.PosterImage
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.core.presentation.RmIcons
import org.ernisernis.ratemoviescmp.movie.domain.Movie
import org.ernisernis.ratemoviescmp.movie.presentation.components.DefaultIconContainer
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components.CastLazyHorizontalRow
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components.DetailRatings
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components.DirectorRow
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components.GenreRow
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components.RatingAvailable
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components.RatingColumn
import org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components.SubtitleRow
import org.ernisernis.ratemoviescmp.movie.presentation.util.bottomInnerShadow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.description_banner
import ratemoviescmp.composeapp.generated.resources.movie_detail_starring
import ratemoviescmp.composeapp.generated.resources.placeholder_gradient


@Composable
fun MovieDetailScreenRoot(
    viewModel: MovieDetailViewModel = koinViewModel(),
    onRateClick: (Movie) -> Unit,
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MovieDetailScreen(
        state = state,
        modifier = Modifier
            .fillMaxSize(),
        onAction = { action ->
            when (action) {
                is MovieDetailAction.OnRateClick -> {
                    state.movie?.let {
                        onRateClick(it)
                    }
                }
                is MovieDetailAction.OnBackClick -> onBackClick()
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
        Box {
            // Banner
            AsyncImage(
                model = state.movieUi?.banner,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .bottomInnerShadow(),
                contentDescription = stringResource(Res.string.description_banner),
                error = painterResource(Res.drawable.placeholder_gradient),
                placeholder = painterResource(Res.drawable.placeholder_gradient),
                fallback = painterResource(Res.drawable.placeholder_gradient),
            )

            // Close, Bookmark buttons
            Row(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(Dimens.MovieDetailItemPaddingBig),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DefaultIconContainer(
                    icon = RmIcons.Back,
                    modifier = Modifier,
                    onClick = {
                        onAction(MovieDetailAction.OnBackClick)
                    }
                )
                DefaultIconContainer(
                    icon = if (state.isBookmarked) {
                        RmIcons.BookmarkSelected
                    } else {
                        RmIcons.BookmarkUnselected
                    },
                    modifier = Modifier,
                    onClick = {
                        onAction(MovieDetailAction.OnBookmarkClick)
                    }
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = Dimens.MovieDetailContainerPadding)
                .offset(y = -(45).dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Title
            state.movieUi?.title?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .weight(1f),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }

            // Image
            state.movieUi?.imageUrl?.let {
                PosterImage(
                    modifier = Modifier
                        .width(60.dp),
                    url = it,
                )
            }
        }

        AnimatedVisibility(
            visible = state.movieUi?.movieDetailUi != null
        ) {
           Column {
               // Subtitle
               SubtitleRow(
                   modifier = Modifier
                       .padding(
                           start = Dimens.MovieDetailItemPaddingBig,
                           bottom = Dimens.MovieDetailItemPaddingNormal,
                       ),
                   releaseDate = state.movieUi?.movieDetailUi?.releaseDate,
                   runtime = state.movieUi?.movieDetailUi?.runtime?.formatted,
               )
               // Ratings
               DetailRatings(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(Dimens.MovieDetailComponentPadding),
                   voteAverage = state.movieUi?.movieDetailUi?.voteAverage,
                   voteCount = state.movieUi?.movieDetailUi?.voteCount,
                   userRatingContent = {
                        if (state.ratingUi != null) {
                            RatingColumn(
                                modifier = Modifier
                                    .clickable {
                                        onAction(MovieDetailAction.OnRateClick)
                                    },
                                icon = RmIcons.StarRate,
                                iconTint = MaterialTheme.colorScheme.secondary,
                                voteAverage = state.ratingUi.userRating.toString(),
                                voteCount = "",
                            )
                        } else {
                            RatingAvailable(
                                modifier = Modifier
                                    .clickable{
                                        onAction(MovieDetailAction.OnRateClick)
                                    },
                                icon = RmIcons.StarOutline,
                                iconTint = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                   },
               )
               // Genre list
               GenreRow(
                   modifier = Modifier
                       .horizontalScroll(rememberScrollState())
                       .padding(Dimens.MovieDetailComponentPadding),
                   genres = state.movieUi?.movieDetailUi?.genres,
               )
               // Overview (movie description)
               state.movieUi?.movieDetailUi?.overview?.let { overview ->
                   Text(
                       text = overview,
                       modifier = Modifier
                           .padding(Dimens.MovieDetailComponentPadding),
                       style = MaterialTheme.typography.bodyMedium,
                       color = MaterialTheme.colorScheme.onBackground.copy(alpha = Dimens.MovieDetailAlpha)
                   )
               }
               // Director, Writer
               DirectorRow(
                   modifier = Modifier
                       .padding(Dimens.MovieDetailComponentPadding),
                   director = state.movieUi?.movieDetailUi?.director,
                   writer = state.movieUi?.movieDetailUi?.writer,
               )

               state.movieUi?.movieDetailUi?.cast?.let { cast ->
                   // Light Divider
                   HorizontalDivider(
                       modifier = Modifier.padding(Dimens.MovieDetailComponentPadding),
                       thickness = 0.5.dp,
                       color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                   )
                   // Cast title
                   Text(
                       text = stringResource(Res.string.movie_detail_starring),
                       modifier = Modifier
                           .padding(Dimens.MovieDetailComponentPadding),
                       style = MaterialTheme.typography.titleLarge,
                       color = MaterialTheme.colorScheme.onBackground,
                   )
                   // Cast tiles
                   CastLazyHorizontalRow(
                       modifier = Modifier
                           .height(200.dp),
                       list = cast,
                   )

               }

               Spacer(modifier = Modifier.height(100.dp))
           }
        }

    }
}