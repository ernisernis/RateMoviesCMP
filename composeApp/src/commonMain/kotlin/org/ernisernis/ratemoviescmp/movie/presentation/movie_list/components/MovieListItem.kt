package org.ernisernis.ratemoviescmp.movie.presentation.movie_list.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi


@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movieUi: MovieUi,
    onClick: () -> Unit,
) {

    Card(
        elevation = 12.dp,
        backgroundColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .clickable(onClick = onClick)
    ) {

    }

//    ElevatedCard(
//        elevation =
//        CardDefaults.cardElevation(
//            defaultElevation = 12.dp,
//        ),
//        colors =
//        CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.secondaryContainer,
//        ),
//        modifier = modifier.clickable(onClick = onClick),
//    ) {
//        AsyncImage(
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(2f / 3f),
//            model = movieUi.imageUrl,
//            contentDescription = null,
//            placeholder = painterResource(R.drawable.poster780w1170hpreview),
//            error = painterResource(R.drawable.poster780w1170hpreview),
//            contentScale = _root_ide_package_.androidx.compose.ui.layout.ContentScale.Fit,
//        )
//
//        // Rating
//        _root_ide_package_.androidx.compose.foundation.layout.Row(
//            horizontalArrangement = _root_ide_package_.androidx.compose.foundation.layout.Arrangement.spacedBy(
//                Dimens.MovieListItemContainerPadding / 2
//            ),
//            verticalAlignment = _root_ide_package_.androidx.compose.ui.Alignment.CenterVertically,
//            modifier =
//            _root_ide_package_.androidx.compose.ui.Modifier
//                .padding(Dimens.MovieListItemContainerPadding),
//        ) {
//            Icon(
//                imageVector = _root_ide_package_.androidx.compose.material.icons.Icons.Default.Star,
//                modifier =
//                _root_ide_package_.androidx.compose.ui.Modifier
//                    .size(Dimens.MovieListItemIconSize),
//                tint = gold,
//                contentDescription = null,
//            )
//            Text(
//                text = movieUi.voteAverage,
//                color = MaterialTheme.colorScheme.onSecondaryContainer,
//                style = MaterialTheme.typography.labelMedium,
//            )
//        }
//
//        // Title
//        Text(
//            text = movieUi.title,
//            modifier =
//            _root_ide_package_.androidx.compose.ui.Modifier
//                .padding(horizontal = Dimens.MovieListItemContainerPadding)
//                .weight(1f),
//            maxLines = 2,
//            overflow = _root_ide_package_.androidx.compose.ui.text.style.TextOverflow.Ellipsis,
//            color = MaterialTheme.colorScheme.onSecondaryContainer,
//            style = MaterialTheme.typography.labelMedium,
//        )
//
//        // Subtitle
//        _root_ide_package_.androidx.compose.foundation.layout.Row(
//            horizontalArrangement = _root_ide_package_.androidx.compose.foundation.layout.Arrangement.spacedBy(
//                Dimens.MovieListItemContainerPadding / 2
//            ),
//            verticalAlignment = _root_ide_package_.androidx.compose.ui.Alignment.CenterVertically,
//            modifier =
//            _root_ide_package_.androidx.compose.ui.Modifier
//                .padding(Dimens.MovieListItemContainerPadding),
//        ) {
//            // Date
//            Text(
//                text = movieUi.releaseDate,
//                color = MaterialTheme.colorScheme.onSurfaceVariant,
//                style = MaterialTheme.typography.labelSmall,
//            )
//
//            _root_ide_package_.androidx.compose.foundation.layout.Spacer(
//                modifier = _root_ide_package_.androidx.compose.ui.Modifier.weight(
//                    1f
//                )
//            )
//
//            // Like icon
//            Icon(
//                imageVector = _root_ide_package_.androidx.compose.material.icons.Icons.Default.ThumbUp,
//                modifier =
//                _root_ide_package_.androidx.compose.ui.Modifier
//                    .size(Dimens.MovieListItemIconSize),
//                tint = MaterialTheme.colorScheme.onSecondaryContainer,
//                contentDescription = null,
//            )
//
//            // Like count
//            Text(
//                text = movieUi.popularity,
//                color = MaterialTheme.colorScheme.onSurfaceVariant,
//                style = MaterialTheme.typography.labelSmall,
//            )
//        }
//    }
}