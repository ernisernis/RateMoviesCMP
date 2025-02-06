package org.ernisernis.ratemoviescmp.app

import androidx.compose.ui.graphics.vector.ImageVector
import org.ernisernis.ratemoviescmp.core.presentation.RmIcons

data class TopLevelRoute<T: Any> (val name: String, val route: T, val icon: ImageVector)

val topLevelRoutes = listOf(
    TopLevelRoute("Home", Route.MovieGraph, RmIcons.Home),
    TopLevelRoute("Bookmark", Route.MovieBookmark, RmIcons.Bookmarks),
    TopLevelRoute("Profile", Route.MovieProfile, RmIcons.Profile)
)