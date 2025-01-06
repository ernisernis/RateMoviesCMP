package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.jetbrains.compose.resources.stringResource
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.description_movie_star


@Composable
fun RatingColumn(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconTint: Color,
    voteAverage: String,
    voteCount: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(Res.string.description_movie_star),
            tint = iconTint,
            modifier = Modifier.size(32.dp),
        )

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = voteAverage,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "/10",
                modifier = Modifier.padding(bottom = 1.dp),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        Text(
            text = voteCount,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = Dimens.MovieDetailAlpha),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}