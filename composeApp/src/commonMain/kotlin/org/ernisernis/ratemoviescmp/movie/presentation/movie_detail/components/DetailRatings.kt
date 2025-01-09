package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.ernisernis.ratemoviescmp.core.presentation.RmIcons


@Composable
fun DetailRatings(
    modifier: Modifier = Modifier,
    voteAverage: String?,
    voteCount: String?,
    onRatingClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        RatingColumn(
            modifier = Modifier,
            icon = RmIcons.StarRate,
            iconTint = MaterialTheme.colorScheme.primary,
            voteAverage = voteAverage ?: "",
            voteCount = voteCount ?: "",
        )
        RatingAvailable(
            modifier = Modifier
                .clickable{
                    onRatingClick()
                },
            icon = RmIcons.StarOutline,
            iconTint = MaterialTheme.colorScheme.onBackground,
        )
    }
}