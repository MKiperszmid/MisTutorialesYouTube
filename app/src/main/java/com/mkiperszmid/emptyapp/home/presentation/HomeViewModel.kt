package com.mkiperszmid.emptyapp.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mkiperszmid.emptyapp.home.domain.usecase.InvalidError
import com.mkiperszmid.emptyapp.home.domain.usecase.ValidatePasswordUseCase
import com.mkiperszmid.emptyapp.home.domain.usecase.ValidationResult

class HomeViewModel(private val validatePasswordUseCase: ValidatePasswordUseCase = ValidatePasswordUseCase()) :
    ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    fun changePassword(password: String) {
        state = state.copy(
            password = password,
        )
    }

    fun validatePassword() {
        val validationResult = validatePasswordUseCase.validate(state.password)
        state = when (validationResult) {
            is ValidationResult.Invalid -> {
                state.copy(
                    passwordError = mapErrorToString(validationResult.error),
                )
            }

            ValidationResult.Valid -> {
                state.copy(
                    passwordError = null,
                )
            }
        }
    }

    private fun mapErrorToString(error: InvalidError): String {
        return when (error) {
            InvalidError.LOW_CHARACTERS -> "No hay caracteres suficientes"
            InvalidError.NO_LOWERCASE -> "No hay minuscula"
            InvalidError.NO_UPPERCASE -> "No hay mayuscula"
            InvalidError.NO_DIGITS -> "No hay numeros"
            InvalidError.NO_SPECIAL_CHARACTERS -> "No hay caracteres especiales"
        }
    }
}
