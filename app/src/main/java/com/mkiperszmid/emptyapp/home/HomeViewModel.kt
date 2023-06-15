package com.mkiperszmid.emptyapp.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    fun aumentarValor() {
        state = state.copy(
            isLoading = true,
        )
        viewModelScope.launch {
            delay(2500)
            state = state.copy(
                currentValue = state.currentValue + 1,
                isLoading = false,
            )
        }
    }
}
