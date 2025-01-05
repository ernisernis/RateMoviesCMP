package org.ernisernis.ratemoviescmp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.ernisernis.ratemoviescmp.app.App
import org.ernisernis.ratemoviescmp.di.initKoin
import org.koin.core.context.startKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "RateMoviesCMP",
        ) {
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false
            )
        }
    }
}