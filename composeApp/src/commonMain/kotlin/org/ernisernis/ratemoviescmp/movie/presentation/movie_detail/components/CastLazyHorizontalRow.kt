package org.ernisernis.ratemoviescmp.movie.presentation.movie_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.movie.presentation.models.CastUi
import org.jetbrains.compose.resources.painterResource
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.poster780w1170hpreview


@Composable
fun CastLazyHorizontalRow(
    modifier: Modifier = Modifier,
    list: List<CastUi>?,
) {
    if (list != null) {
        LazyHorizontalGrid(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(Dimens.MovieDetailContainerPadding),
            contentPadding = Dimens.MovieDetailComponentPadding,
            rows = GridCells.Fixed(2),
        ) {
            items(items = list, key = { it.id } ) { castUi ->
                CastItem(castUi)
            }
        }
    }
}

@Composable
fun CastItem(castUi: CastUi) {
    Row(
        modifier = Modifier
            .width(264.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = castUi.profilePath,
            modifier = Modifier
                .width(60.dp)
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(20)),
            contentDescription = null,
            error = painterResource(Res.drawable.poster780w1170hpreview),
        )
        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.MovieDetailContainerPadding),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = castUi.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = castUi.character,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = Dimens.MovieDetailAlpha),
            )
        }
    }
}