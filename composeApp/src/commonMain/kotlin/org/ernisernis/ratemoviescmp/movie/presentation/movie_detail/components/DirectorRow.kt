package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.movie_detail_director
import ratemoviescmp.composeapp.generated.resources.movie_detail_writer


@Composable
fun DirectorRow(
    modifier: Modifier = Modifier,
    director: String?,
    writer: String?,
) {
    val directorText =
        if (director != null) {
            buildAnnotatedString {
                append(stringResource(Res.string.movie_detail_director))
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(director)
                }
            }
        } else {
            null
        }

    val writerText =
        if (writer != null) {
            buildAnnotatedString {
                append(stringResource(Res.string.movie_detail_writer))
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(writer)
                }
            }
        } else {
            null
        }

    val shouldShowVBar = director != null && writer != null

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        directorText?.let { text ->
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        if (shouldShowVBar) {
            VerticalDivider(
                modifier = Modifier.height(14.dp),
                thickness = 1.5.dp,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        writerText?.let { text ->
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}