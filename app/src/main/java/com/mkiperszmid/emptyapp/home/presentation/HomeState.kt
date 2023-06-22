package com.mkiperszmid.emptyapp.home.presentation

data class HomeState(
    val password: String = "",
    val passwordError: String? = null
)
