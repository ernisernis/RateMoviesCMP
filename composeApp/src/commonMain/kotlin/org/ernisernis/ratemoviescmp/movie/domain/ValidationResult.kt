package org.ernisernis.ratemoviescmp.movie.domain

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)