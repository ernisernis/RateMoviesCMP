package org.ernisernis.ratemoviescmp.core.presentation

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorPalette = darkColorScheme(
    // Main brand color used for prominent UI elements like buttons, active states, and app bars.
    primary = Color(0xFFE5AC00),
    // Text/icon color used on top of the primary color surface to ensure readability.
    onPrimary = Color(0xFF000000),

    // Used for less prominent elements that still need attention, such as floating action buttons (FABs), switches, or selection controls.
    secondary = Color(0xFF9CCDEE),
    // Text/icon color on top of a secondary surface.
    onSecondary = Color(0xFF00344B),

    // Background color of the entire app screen.
    background = Color(0xFF171309),
    // Text/icon color on top of the background surface.
    onBackground = Color(0xFFECE1D1),

    // Background color for surfaces like cards, dialogs, and sheets.
    surface = Color(0xFF2A2B2A),
    // Text/icon color used on surface components.
    onSurface = Color(0xFFE6E2E0),

    // Used to represent error states in UI elements.
    error = Color(0xFFFFB4AB),
    // Text/icon color used on error surfaces.
    onError = Color(0xFF690005)
)

val LightColorPalette = lightColorScheme(
    // Main brand color used for prominent UI elements like buttons, active states, and app bars.
    primary = Color(0xFFE5AC00),
    // Text/icon color used on top of the primary color surface to ensure readability.
    onPrimary = Color(0xFF000000),

    // Used for less prominent elements that still need attention, such as floating action buttons (FABs), switches, or selection controls.
    secondary = Color(0xFF9CCDEE),
    // Text/icon color on top of a secondary surface.
    onSecondary = Color(0xFF00344B),

    // Background color of the entire app screen.
    background = Color(0xFF171309),
    // Text/icon color on top of the background surface.
    onBackground = Color(0xFFECE1D1),

    // Background color for surfaces like cards, dialogs, and sheets.
    surface = Color(0xFF2A2B2A),
    // Text/icon color used on surface components.
    onSurface = Color(0xFFE6E2E0),

    // Used to represent error states in UI elements.
    error = Color(0xFFFFB4AB),
    // Text/icon color used on error surfaces.
    onError = Color(0xFF690005)
)

// TODO: even though actual implementations are the same, in future it is easier to implement dynamic coloring for Android specifically
@Composable
expect fun RateMoviesTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)