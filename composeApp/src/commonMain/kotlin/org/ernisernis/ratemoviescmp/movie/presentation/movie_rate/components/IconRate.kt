package org.ernisernis.ratemoviescmp.movie.presentation.movie_rate.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.stringResource
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.description_movie_star


@Composable
fun IconRate(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    Icon(
        imageVector = icon,
        contentDescription = stringResource(Res.string.description_movie_star),
        tint = MaterialTheme.colorScheme.onBackground,
        modifier =
        modifier.clickable(enabled = true) {
            onClick()
        },
    )
}