package com.supercerebros.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.supercerebros.ui.theme.SupercerebrosTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildRegistrationScreen(
    onRegisterClick: (String, String, String, String, String, String) -> Unit, // En el navController debo poner todos estos campos,  nombres de variables que representen a lo que recibirán
    onBackClick: () -> Unit // Acción para el botón de volver atrás
) {
    val name = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val birthDate = remember { mutableStateOf("") }
    val gender = remember { mutableStateOf("") }
    val dni = remember { mutableStateOf("") }
    val medicalInfo = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar Niño") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = lastName.value,
                onValueChange = { lastName.value = it },
                label = { Text("Apellidos") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = birthDate.value,
                onValueChange = { birthDate.value = it },
                label = { Text("Fecha de Nacimiento") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .clickable {
                        // Aquí puedo abrir un DatePicker
                    },
                singleLine = true,
                readOnly = true
            )

            OutlinedTextField(
                value = gender.value,
                onValueChange = { gender.value = it },
                label = { Text("Género") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = dni.value,
                onValueChange = { dni.value = it },
                label = { Text("DNI") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = medicalInfo.value,
                onValueChange = { medicalInfo.value = it },
                label = { Text("Información Médica") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onRegisterClick(
                        name.value,
                        lastName.value,
                        birthDate.value,
                        gender.value,
                        dni.value,
                        medicalInfo.value
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Niño")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChildRegistrationScreenPreview() {
    SupercerebrosTheme {
        ChildRegistrationScreen(
            onRegisterClick = { _, _, _, _, _, _ -> },
            onBackClick = {}
        )
    }
}
