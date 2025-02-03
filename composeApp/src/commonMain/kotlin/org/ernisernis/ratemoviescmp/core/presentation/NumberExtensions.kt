package org.ernisernis.ratemoviescmp.core.presentation

import kotlin.math.pow
import kotlin.math.round

internal fun Double.fractionDigits(fractionDigits: Int): String {
    val factor = 10.0.pow(fractionDigits)
    val rounded = round(factor * this) / factor
    return if (rounded % 1.0 == 0.0) rounded.toInt().toString() else rounded.toString()
}
