package com.mkiperszmid.emptyapp.home.domain.usecase

class ValidatePasswordUseCase {
    fun validate(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult.Invalid(InvalidError.LOW_CHARACTERS)
        }
        if (!password.any { it.isLowerCase() }) {
            return ValidationResult.Invalid(InvalidError.NO_LOWERCASE)
        }

        if (!password.any { it.isUpperCase() }) {
            return ValidationResult.Invalid(InvalidError.NO_UPPERCASE)
        }

        if (!password.any { it.isDigit() }) {
            return ValidationResult.Invalid(InvalidError.NO_DIGITS)
        }

        if (!password.any { !it.isLetter() && !it.isDigit() }) {
            return ValidationResult.Invalid(InvalidError.NO_SPECIAL_CHARACTERS)
        }

        return ValidationResult.Valid
    }
}

sealed interface ValidationResult {
    object Valid : ValidationResult
    data class Invalid(val error: InvalidError) : ValidationResult
}

enum class InvalidError {
    LOW_CHARACTERS,
    NO_LOWERCASE,
    NO_UPPERCASE,
    NO_DIGITS,
    NO_SPECIAL_CHARACTERS,
}
