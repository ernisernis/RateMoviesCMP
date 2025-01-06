package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ernisernis.ratemoviescmp.movie.presentation.models.MovieGenreUi

@Composable
fun GenreRow(
    modifier: Modifier = Modifier,
    genres: List<MovieGenreUi>?,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        genres?.forEach { genre ->
            Box(
                modifier =
                Modifier.background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(50),
                ).padding(vertical = 8.dp, horizontal = 14.dp),
            ) {
                Text(
                    text = genre.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}