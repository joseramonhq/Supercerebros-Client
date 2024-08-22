package com.supercerebros.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.supercerebros.MyApplication
import com.supercerebros.models.Child
import com.supercerebros.ui.theme.SupercerebrosTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildRegistrationScreen(
    navController: NavHostController?=null,
    onRegisterClick: (String, String, String, String, String, String, String, Boolean) -> Unit, // Incluyendo el campo password
    onBackClick: () -> Unit // Acción para el botón de volver atrás
) {
    val context = LocalContext.current
    val app = context.applicationContext as MyApplication
    val currentUser = app.currentUser

    val name = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val birthDate = remember { mutableStateOf("") }
    val gender = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
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
                },
                actions = {
                    // Mostrar el nombre del usuario en la esquina superior derecha
                    if (currentUser != null) {
                        Text(
                            text = currentUser.toString(), // Mostrar el nombre del usuario
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            item {
                OutlinedTextField(
                    value = lastName.value,
                    onValueChange = { lastName.value = it },
                    label = { Text("Apellidos") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            item {

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
            }
            item {
                OutlinedTextField(
                    value = gender.value,
                    onValueChange = { gender.value = it },
                    label = { Text("Género") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            item {
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("DNI") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            // Campo de contraseña añadido aquí
            item {
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
            item {

                OutlinedTextField(
                    value = medicalInfo.value,
                    onValueChange = { medicalInfo.value = it },
                    label = { Text("Información Médica") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {

                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Button(
                    onClick = {
                        onRegisterClick(
                            name.value,
                            lastName.value,
                            birthDate.value,
                            gender.value,
                            email.value,
                            password.value,
                            medicalInfo.value,
                            true
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar Niño")
                }
            }
        }
    }
}
@Composable
fun ChildRegistrationScreenPreview() {
    SupercerebrosTheme {
        ChildRegistrationScreen(
            onRegisterClick = { _, _, _, _, _, _, _, _ -> },
            onBackClick = {}
        )
    }
}
