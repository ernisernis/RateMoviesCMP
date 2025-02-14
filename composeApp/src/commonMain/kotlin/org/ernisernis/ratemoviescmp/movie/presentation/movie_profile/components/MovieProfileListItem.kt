package org.ernisernis.ratemoviescmp.movie.presentation.movie_profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import org.ernisernis.ratemoviescmp.core.presentation.Dimens
import org.ernisernis.ratemoviescmp.core.presentation.RmIcons
import org.ernisernis.ratemoviescmp.movie.presentation.components.PosterImage
import org.ernisernis.ratemoviescmp.movie.presentation.models.RatingUi
import org.jetbrains.compose.resources.stringResource
import ratemoviescmp.composeapp.generated.resources.Res
import ratemoviescmp.composeapp.generated.resources.description_movie_star
import ratemoviescmp.composeapp.generated.resources.more_options
import ratemoviescmp.composeapp.generated.resources.profile_review_delete


@Composable
fun MovieProfileListItem(
    modifier: Modifier = Modifier,
    ratingUi: RatingUi,
    description: @Composable () -> Unit,
    onClick: () -> Unit,
    onTooltipClick: () -> Unit,
    onDismissTooltip: () -> Unit,
    onReviewDelete: () -> Unit,
) {
   Row(
       modifier = modifier
           .background(MaterialTheme.colorScheme.surface)
           .fillMaxWidth()
           .clickable(onClick = onClick)
           .padding(Dimens.ProfileItemPaddingNormal),
       horizontalArrangement = Arrangement.spacedBy(Dimens.ProfileItemPaddingNormal),
   ) {

       PosterImage(
           modifier = Modifier
               .width(Dimens.ProfileRatingItemImageWidth),
           url = ratingUi.imageUrl,
       )

       Column {
           Row(
               verticalAlignment = Alignment.CenterVertically,
           ) {
               Column(
                   modifier = Modifier
                       .weight(1f),
                   verticalArrangement = Arrangement.spacedBy(Dimens.ProfileItemPaddingSmall),
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
               }

               Box {
                   DropdownMenu(
                       expanded = ratingUi.tooltip,
                       onDismissRequest = {
                           onDismissTooltip()
                       },
                       containerColor = MaterialTheme.colorScheme.secondary
                   ) {
                       DropdownMenuItem(
                           text = {
                               Text(
                                   text = stringResource(Res.string.profile_review_delete),
                                   color = MaterialTheme.colorScheme.onSecondary,
                               )
                           },
                           onClick = {
                                onReviewDelete()
                           }
                       )
                   }
                   IconButton(
                       onClick = {
                           onTooltipClick()
                       }
                   ) {
                       Icon(
                           imageVector = RmIcons.MoreVert,
                           contentDescription = stringResource(Res.string.more_options),
                           tint = MaterialTheme.colorScheme.onSurface,
                       )
                   }
               }

           }

           description()
       }

   }
}