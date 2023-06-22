package com.mkiperszmid.emptyapp.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val requirements = buildAnnotatedString {
            append("Requisitos:\n")
            append("- +8 Caracteres\n")
            append("- 1 Mayúscula\n")
            append("- 1 Minúscula\n")
            append("- 1 Núero\n")
            append("- 1 Caracter especial\n")
        }
        Text(text = requirements, modifier = Modifier.align(Alignment.Start))
        TextField(
            value = state.password,
            placeholder = { Text(text = "Contraseña") },
            onValueChange = viewModel::changePassword,
            modifier = Modifier.fillMaxWidth(),
        )
        if (state.passwordError != null) {
            Text(text = state.passwordError, color = Color.Red)
        }
        Button(
            onClick = viewModel::validatePassword,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Validar")
        }
    }
}
