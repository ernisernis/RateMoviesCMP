package org.ernisernis.ratemoviescmp.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ernisernis.ratemoviescmp.core.presentation.RateMoviesTheme

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    RateMoviesTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .size(100.dp)
            ) {

            }
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .size(100.dp)
            ) {

            }
        }
    }
}