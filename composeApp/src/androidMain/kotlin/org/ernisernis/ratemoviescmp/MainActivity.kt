package org.ernisernis.ratemoviescmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import org.ernisernis.ratemoviescmp.app.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false
            )
        }
    }
}