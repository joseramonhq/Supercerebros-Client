package com.supercerebros.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.supercerebros.ui.theme.SupercerebrosTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordRecoveryScreen(
    navController: NavController,
    onSendCodeClick: (String) -> Unit,
    onVerifyCodeClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val code = remember { mutableStateOf("") }
    val isCodeFieldEnabled = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recuperar Contraseña") },
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
            Text(
                text = "Introduce tu correo electrónico para recibir un código de verificación.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Correo Electrónico") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                visualTransformation = VisualTransformation.None,
                modifier = Modifier.padding(bottom = 16.dp),
                singleLine = true


            )

            Button(
                onClick = {
                    onSendCodeClick(email.value)
                    isCodeFieldEnabled.value = true // Habilitar el campo de código y el botón
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Recibir Código")
            }

            OutlinedTextField(
                value = code.value,
                onValueChange = { code.value = it },
                label = { Text("Introduce el Código") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = VisualTransformation.None,
                enabled = isCodeFieldEnabled.value, // Mantener deshabilitado hasta que se reciba el código
                modifier = Modifier.padding(bottom = 16.dp),
                singleLine = true

            )

            Button(
                onClick = { onVerifyCodeClick(code.value) },
                enabled = isCodeFieldEnabled.value, // Mantener deshabilitado hasta que se reciba el código
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Verificar Código")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordRecoveryScreenPreview() {
    SupercerebrosTheme {
        PasswordRecoveryScreen(
            navController = rememberNavController(),
            onSendCodeClick = {},
            onVerifyCodeClick = {},
            onBackClick = {}
        )
    }
}
