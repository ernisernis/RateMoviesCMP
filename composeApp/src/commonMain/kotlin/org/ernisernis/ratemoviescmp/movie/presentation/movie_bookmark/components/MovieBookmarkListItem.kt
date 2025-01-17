package org.ernisernis.ratemoviescmp.movie.presentation.movie_bookmark.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieUi


@Composable
fun MovieBookmarkListItem(
    modifier: Modifier = Modifier,
    movieUi: MovieUi,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .padding(Dimens.MovieBookmarkContainerPadding)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(30)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(Dimens.MovieBookmarkItemPaddingSmall),
            ) {
            }
        }
    }
}