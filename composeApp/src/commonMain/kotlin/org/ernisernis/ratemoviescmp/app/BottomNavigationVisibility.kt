package org.ernisernis.ratemoviescmp.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable


@Composable
fun BottomNavigationVisibility(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
   AnimatedVisibility(
       visible = visible,
       enter = slideInVertically(initialOffsetY = { it }),
       exit = slideOutVertically(targetOffsetY = { it }),
       content = content,
   )
}