package org.ernisernis.ratemoviescmp.core.presentation

import kotlin.math.round

// String should always be between 0 and 10
internal fun Double.formatVoteAverage(): String {
    val safeDouble = this.coerceIn(0.0, 10.0)
    val rounded = round(10.0 * safeDouble) / 10.0
    return if (rounded % 1.0 == 0.0) rounded.toInt().toString() else rounded.toString()
}

// Get a release year from `releaseDate` field
internal fun String.getReleaseYear(): String {
    return this.substringBefore("-")
}