package org.ernisernis.ratemoviescmp.movie.presentation.movie_profile.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.ernisernis.ratemoviescmp.core.presentation.Dimens

@Composable
fun ProfileRatingDescription(
    modifier: Modifier = Modifier,
    text: String,
    extended: Boolean
) {
    Text(
        text = text,
        modifier = modifier
            .padding(top = Dimens.ProfileItemPaddingSmall),
        color = MaterialTheme.colorScheme.onSurface,
        maxLines = if (extended) {
            Int.MAX_VALUE
        } else {
            3
        },
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium,
    )
}