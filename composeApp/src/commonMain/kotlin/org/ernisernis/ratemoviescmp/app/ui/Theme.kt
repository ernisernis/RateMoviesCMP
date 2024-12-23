package org.ernisernis.ratemoviescmp.app.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    // Main brand color used for prominent UI elements like buttons, active states, and app bars.
    primary = Color(0xFFE5AC00),
    // A darker variant of the primary color, often used to show elevation or differentiation of UI elements.
    primaryVariant = Color(0xFF372700),
    // Text/icon color used on top of the primary color surface to ensure readability.
    onPrimary = Color(0xFF000000),

    // Used for less prominent elements that still need attention, such as floating action buttons (FABs), switches, or selection controls.
    secondary = Color(0xFF9CCDEE),
    // A darker variant of the secondary color.
    secondaryVariant = Color(0xff7ca4b9),
    // Text/icon color on top of a secondary surface.
    onSecondary = Color(0xFF00344B),

    // Background color of the entire app screen.
    background = Color(0xFF171309),
    // Text/icon color on top of the background surface.
    onBackground = Color(0xFFECE1D1),

    // Background color for surfaces like cards, dialogs, and sheets.
    surface = Color(0xFF141313),
    // Text/icon color used on surface components.
    onSurface = Color(0xFFE6E2E0),

    // Used to represent error states in UI elements.
    error = Color(0xFFFFB4AB),
    // Text/icon color used on error surfaces.
    onError = Color(0xFF690005)
)

private val LightColorPalette = lightColors(
    // Main brand color used for prominent UI elements like buttons, active states, and app bars.
    primary = Color(0xFFE5AC00),
    // A darker variant of the primary color, often used to show elevation or differentiation of UI elements.
    primaryVariant = Color(0xFF372700),
    // Text/icon color used on top of the primary color surface to ensure readability.
    onPrimary = Color(0xFF000000),

    // Used for less prominent elements that still need attention, such as floating action buttons (FABs), switches, or selection controls.
    secondary = Color(0xFF9CCDEE),
    // A darker variant of the secondary color.
    secondaryVariant = Color(0xff7ca4b9),
    // Text/icon color on top of a secondary surface.
    onSecondary = Color(0xFF00344B),

    // Background color of the entire app screen.
    background = Color(0xFF171309),
    // Text/icon color on top of the background surface.
    onBackground = Color(0xFFECE1D1),

    // Background color for surfaces like cards, dialogs, and sheets.
    surface = Color(0xFF141313),
    // Text/icon color used on surface components.
    onSurface = Color(0xFFE6E2E0),

    // Used to represent error states in UI elements.
    error = Color(0xFFFFB4AB),
    // Text/icon color used on error surfaces.
    onError = Color(0xFF690005)
)

@Composable
fun RateMoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}