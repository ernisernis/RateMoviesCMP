package org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.core.presentation.RmIcons
import org.ernisernis.ratemoviescmp.movie.presentation.components.DefaultIconContainer
import org.ernisernis.ratemoviescmp.movie.presentation.components.PosterImage
import org.ernisernis.ratemoviescmp.movie.presentation.models.BookmarkMovieUi
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi
import org.jetbrains.compose.resources.stringResource
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.description_movie_star


@Composable
fun MovieBookmarkListItem(
    modifier: Modifier = Modifier,
    bookmarkMovieUi: BookmarkMovieUi,
    onClick: () -> Unit,
    onBookmarkClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(Dimens.MovieBookmarkItemPaddingNormal),
        horizontalArrangement = Arrangement.spacedBy(Dimens.MovieBookmarkItemPaddingNormal),
        verticalAlignment = Alignment.CenterVertically
    ) {

        PosterImage(
            modifier = Modifier
                .width(Dimens.MovieBookmarkImageWidth),
            url = bookmarkMovieUi.imageUrl,
        )

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(Dimens.MovieBookmarkItemPaddingSmall, Alignment.CenterVertically),
        ) {
            // Title
            Text(
                text = bookmarkMovieUi.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp,
            )

            // Year + runtime
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimens.MovieBookmarkItemPaddingNormal)
            ) {
                Text(
                    text = bookmarkMovieUi.releaseYear,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = Dimens.MovieBookmarkAlpha),
                    style = MaterialTheme.typography.bodyMedium,
                )
                bookmarkMovieUi.runtimeFormatted?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = Dimens.MovieBookmarkAlpha),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            // Star icon + rating
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(Dimens.MovieBookmarkItemPaddingSmall),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = RmIcons.StarRate,
                    contentDescription = stringResource(Res.string.description_movie_star),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(24.dp)
                )
                Text(
                    text = bookmarkMovieUi.voteAverage,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        DefaultIconContainer(
            icon = RmIcons.BookmarkSelected,
            modifier = Modifier
                .align(Alignment.Top),
            onClick = {
                onBookmarkClick()
            }
        )
    }
}