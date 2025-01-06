package org.ernisernis.ratemoviescmp.core.presentation.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.description_poster
import ratemoviescmp.composeapp.generated.resources.poster780w1170hpreview


@Composable
fun PosterImage(
    modifier: Modifier = Modifier,
    url: String,
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
    ) {
        AsyncImage(
            modifier = modifier
                .aspectRatio(2f / 3f),
            model = url,
            contentDescription = stringResource(Res.string.description_poster),
            placeholder = painterResource(Res.drawable.poster780w1170hpreview),
            error = painterResource(Res.drawable.poster780w1170hpreview),
            contentScale = ContentScale.Fit,
        )
    }
}