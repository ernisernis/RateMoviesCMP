package org.ernisernis.ratemoviescmp.core.presentation

import kotlin.math.round

internal fun Double.formatVoteAverage(): String {
    val rounded = round(10.0 * this) / 10.0
    return if (rounded % 1.0 == 0.0) rounded.toInt().toString() else rounded.toString()
}
