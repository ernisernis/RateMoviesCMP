package org.ernisernis.ratemoviescmp.movie.presentation.models.use_case

import org.ernisernis.ratemoviescmp.movie.domain.ValidationResult

class ValidateRateNumber {

    fun execute(number: Int): ValidationResult {
        if (number == 0) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please provide a rating!"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}