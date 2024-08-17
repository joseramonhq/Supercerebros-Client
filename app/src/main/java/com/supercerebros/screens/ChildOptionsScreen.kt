package com.supercerebros.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun ChildOptionsScreen(
    navController: NavHostController? = null,
    onBackClick: (() -> Unit)? = null
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Añade un margen de 16dp alrededor de la columna
    ) {
        Button(
            onClick = { /* Acción para registrar un niño */ },
            modifier = Modifier.padding(vertical = 8.dp) // Añade un espacio vertical entre los botones
        ) {
            Text(text = "Registrar niño")
        }

        Button(
            onClick = { /* Acción para borrar un niño */ },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Borrar niño")
        }

        Button(
            onClick = { /* Acción para modificar un niño */ },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Modificar niño")
        }
    }
}



