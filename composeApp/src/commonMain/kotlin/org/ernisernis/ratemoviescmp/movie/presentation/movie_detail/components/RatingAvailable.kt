package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.description_movie_star
import ratemoviescmp.composeapp.generated.resources.movie_detail_rate


@Composable
fun RatingAvailable(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconTint: Color,
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

        Text(
            text = stringResource(Res.string.movie_detail_rate),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}