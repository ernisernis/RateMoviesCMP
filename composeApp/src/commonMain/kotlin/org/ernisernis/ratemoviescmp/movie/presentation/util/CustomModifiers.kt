package org.ernisernis.ratemoviescmp.movie.presentation.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.ernisernis.ratemoviescmp.core.presentation.Dimens


// Custom modifier for bottom inner shadow
fun Modifier.bottomInnerShadow(
    shadowColor: Color = Color.Black,
    shadowAlpha: Float = Dimens.MovieDetailAlpha,
    shadowHeightFraction: Float = 0.6f,
) = this.then(
    Modifier.drawWithContent {
        drawContent()

        // Define the gradient brush for the inner shadow
        val shadowHeight = size.height * shadowHeightFraction
        val shadowBrush =
            Brush.verticalGradient(
                colors =
                listOf(
                    Color.Transparent, // Top of the shadow (transparent)
                    shadowColor.copy(alpha = shadowAlpha), // Bottom shadow color
                ),
                startY = size.height - shadowHeight,
                endY = size.height,
            )

        // Draw the shadow on top of the content
        drawRect(
            brush = shadowBrush,
            size = size,
        )
    },
)