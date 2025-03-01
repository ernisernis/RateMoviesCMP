package org.ernisernis.ratemoviescmp

import androidx.compose.ui.window.ComposeUIViewController
import org.ernisernis.ratemoviescmp.app.App
import org.ernisernis.ratemoviescmp.di.initKoin
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false
    )
}