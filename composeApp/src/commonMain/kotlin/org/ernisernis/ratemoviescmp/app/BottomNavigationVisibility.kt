package org.ernisernis.ratemoviescmp.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun BottomNavigationVisibility(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
   AnimatedVisibility(
       modifier = Modifier
           .background(MaterialTheme.colorScheme.surface),
       visible = visible,
       enter = slideInVertically(initialOffsetY = { it }),
       exit = slideOutVertically(targetOffsetY = { it }),
       content = content,
   )
}