package org.ernisernis.ratemoviescmp.movie.presentation.movie_profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.core.presentation.RmIcons
import org.ernisernis.ratemoviescmp.movie.presentation.components.PosterImage
import org.ernisernis.ratemoviescmp.movie.presentation.models.RatingUi
import org.jetbrains.compose.resources.stringResource
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.description_movie_star


@Composable
fun MovieProfileListItem(
    modifier: Modifier = Modifier,
    ratingUi: RatingUi,
    description: @Composable () -> Unit,
    onClick: () -> Unit,
) {
   Row(
       modifier = modifier
           .background(MaterialTheme.colorScheme.surface)
           .fillMaxWidth()
           .clickable(onClick = onClick)
           .padding(Dimens.ProfileItemPaddingNormal),
       horizontalArrangement = Arrangement.spacedBy(Dimens.ProfileItemPaddingNormal),
       verticalAlignment = Alignment.CenterVertically
   ) {

       PosterImage(
           modifier = Modifier
               .width(Dimens.MovieBookmarkImageWidth),
           url = ratingUi.imageUrl,
       )

       Column(
           modifier = Modifier
               .weight(1f),
           verticalArrangement = Arrangement.spacedBy(Dimens.ProfileItemPaddingSmall, Alignment.CenterVertically),
       ) {
           // Title
           Text(
               text = ratingUi.title,
               color = MaterialTheme.colorScheme.onSurface,
               style = MaterialTheme.typography.bodyLarge,
               maxLines = 2,
               overflow = TextOverflow.Ellipsis,
               lineHeight = 18.sp,
           )

           // Year + runtime
           Row(
               horizontalArrangement = Arrangement.spacedBy(Dimens.ProfileItemPaddingNormal)
           ) {
               Text(
                   text = ratingUi.releaseYear,
                   color = MaterialTheme.colorScheme.onSurface.copy(alpha = Dimens.ProfileAlpha),
                   style = MaterialTheme.typography.bodyMedium,
               )
               ratingUi.runtimeFormatted?.let {
                   Text(
                       text = it,
                       color = MaterialTheme.colorScheme.onSurface.copy(alpha = Dimens.ProfileAlpha),
                       style = MaterialTheme.typography.bodyMedium,
                   )
               }
           }

           // Movie rating + User rating
           Row(
               modifier = Modifier,
               horizontalArrangement = Arrangement.spacedBy(Dimens.ProfileItemPaddingSmall),
               verticalAlignment = Alignment.CenterVertically,
           ) {
               Icon(
                   imageVector = RmIcons.StarRate,
                   contentDescription = stringResource(Res.string.description_movie_star),
                   tint = MaterialTheme.colorScheme.primary,
                   modifier = Modifier
                       .size(Dimens.ProfileIconSize)
               )
               Text(
                   text = ratingUi.voteAverage,
                   color = MaterialTheme.colorScheme.onSurface,
                   style = MaterialTheme.typography.bodyMedium,
               )

               Spacer(modifier = Modifier.width(Dimens.ProfileItemPaddingSmall))

               Icon(
                   imageVector = RmIcons.StarRate,
                   contentDescription = stringResource(Res.string.description_movie_star),
                   tint = MaterialTheme.colorScheme.secondary,
                   modifier = Modifier
                       .size(Dimens.ProfileIconSize)
               )
               Text(
                   text = ratingUi.userRating.toString(),
                   color = MaterialTheme.colorScheme.onSurface,
                   style = MaterialTheme.typography.bodyMedium,
               )
           }

           description()
       }
   }
}