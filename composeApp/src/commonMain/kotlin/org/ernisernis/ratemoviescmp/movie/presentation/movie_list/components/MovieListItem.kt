package org.ernisernis.ratemoviescmp.movie.presentation.movie_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.core.presentation.RmIcons
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.description_movie_item
import ratemoviescmp.composeapp.generated.resources.description_movie_star
import ratemoviescmp.composeapp.generated.resources.description_movie_thumb_up
import ratemoviescmp.composeapp.generated.resources.placeholder_gradient


@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movieUi: MovieUi,
    onClick: () -> Unit,
) {

    Surface(
        shadowElevation = 12.dp,
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(5),
        modifier = modifier
            .clickable(onClick = onClick)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Image
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f),
                model = movieUi.imageUrl,
                contentDescription = stringResource(Res.string.description_movie_item),
                error = painterResource(Res.drawable.placeholder_gradient),
                placeholder = painterResource(Res.drawable.placeholder_gradient),
                contentScale = ContentScale.Fit
            )

            // Rating row (Icon + Vote text)
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimens.MovieListItemPaddingSmall),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(Dimens.MovieListItemPaddingNormal)
            ) {
                Icon(
                    imageVector = RmIcons.Star,
                    modifier = Modifier
                        .size(Dimens.MovieListItemIconSize),
                    contentDescription = stringResource(Res.string.description_movie_star),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = movieUi.voteAverage,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelMedium,
                )
            }

            // Title
            Text(
                text = movieUi.title,
                modifier = Modifier
                    .padding(horizontal = Dimens.MovieListItemPaddingNormal)
                    .weight(1f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
            )

            // Subtitle row
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimens.MovieListItemPaddingSmall),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(Dimens.MovieListItemPaddingNormal)
            ) {
                // Date
                Text(
                    text = movieUi.releaseDate,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelSmall,
                )

                Spacer(modifier = Modifier.weight(1f))

                // Like icon
                Icon(
                    imageVector = RmIcons.ThumbUp,
                    modifier = Modifier
                        .size(Dimens.MovieListItemIconSize),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = stringResource(Res.string.description_movie_thumb_up),
                )

                // Like count
                Text(
                    text = movieUi.popularity,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}