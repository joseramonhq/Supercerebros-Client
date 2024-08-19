package com.supercerebros.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SuccessScreen(navController: NavHostController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Registro Exitoso",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "¡Bienvenido a la comunidad Supercerebros!",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                // Navega a la pantalla de inicio o de inicio de sesión
                navController?.navigate("tutorMenu") // Cambia "homeScreen" por la ruta de tu pantalla de inicio
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a la pantalla principal")
        }
    }
}


