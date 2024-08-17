package com.supercerebros.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.supercerebros.R
import com.supercerebros.ui.theme.SupercerebrosTheme

private const val s = "Correo Electrónico"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController, // Añadimos NavController como parámetro
    onLoginClick: (String, String) -> Unit,
   // onBackClick: () -> Unit, // Acción para el botón de volver atrás
    onRegisterClick: () -> Unit // Acción para el botón de registrarse
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.login)) },
              /*  navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás",
                        )
                    }
                },*/
                actions = {
                    Text(
                        text = stringResource(R.string.sign_up),
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable(onClick = onRegisterClick)
                    )
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
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(stringResource(R.string.email)) },
                modifier = Modifier.padding(bottom = 16.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text(stringResource(id = R.string.password)) },
                modifier = Modifier.padding(bottom = 16.dp),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )

            Text(
                text = stringResource(R.string.forgoten_password),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable {
                        navController.navigate("passwordRecovery") // Navegar a la pantalla de recuperación de contraseña
                    }
            )

            Button(
                onClick = { onLoginClick(email.value, password.value) },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Iniciar Sesión")
            }

            Button(
                onClick = { navController.navigate("tutorMenu") },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Iniciar Sesión")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    SupercerebrosTheme {
        LoginScreen(
            navController = navController,
            onLoginClick = { _, _ -> },
           // onBackClick = {},
            onRegisterClick = {} // Añadir callback para la acción de registro
        )
    }
}

