package com.myapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapp.R
import com.myapp.components.textComponents.sampledata.CustomText
import com.myapp.components.textComponents.sampledata.TextData
import com.myapp.components.textInputComponents.sampleData.CustomOutlinedTextField
import com.myapp.components.textInputComponents.sampleData.OutlinedTextFieldData

/**
 * Pantalla de registro de usuario.
 *
 * @param navController Controlador de navegación para manejar la navegación entre pantallas.
 */
@Composable
fun RegistrationScreen(navController: NavController) {
    // Definición de los estados para cada campo de entrada
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    // Composición principal de la pantalla de registro
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // Permite el desplazamiento vertical
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título de la pantalla de registro
        CustomText(
            data = TextData(
                text = stringResource(id = R.string.register),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada para el nombre completo
        Box(modifier = Modifier.align(alignment = Alignment.Start)) {
            CustomText(data = TextData(text = stringResource(id = R.string.full_name)))
        }
        CustomOutlinedTextField(
            data = OutlinedTextFieldData(
                value = fullName,
                onValueChange = { fullName = it },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.full_name),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de entrada para el correo electrónico
        Box(modifier = Modifier.align(alignment = Alignment.Start)) {
            CustomText(data = TextData(text = stringResource(id = R.string.email)))
        }
        CustomOutlinedTextField(
            data = OutlinedTextFieldData(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.email),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de entrada para la contraseña
        Box(modifier = Modifier.align(alignment = Alignment.Start)) {
            CustomText(data = TextData(text = stringResource(id = R.string.password)))
        }
        CustomOutlinedTextField(
            data = OutlinedTextFieldData(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.password),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de entrada para la fecha de nacimiento
        Box(modifier = Modifier.align(alignment = Alignment.Start)) {
            CustomText(data = TextData(text = stringResource(id = R.string.birth_date)))
        }
        CustomOutlinedTextField(
            data = OutlinedTextFieldData(
                value = birthDate,
                onValueChange = { birthDate = it },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.birth_date),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de entrada para el teléfono
        Box(modifier = Modifier.align(alignment = Alignment.Start)) {
            CustomText(data = TextData(text = stringResource(id = R.string.phone)))
        }
        CustomOutlinedTextField(
            data = OutlinedTextFieldData(
                value = phone,
                onValueChange = { phone = it },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.phone),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de entrada para el género
        Box(modifier = Modifier.align(alignment = Alignment.Start)) {
            CustomText(data = TextData(text = stringResource(id = R.string.gender)))
        }
        CustomOutlinedTextField(
            data = OutlinedTextFieldData(
                value = gender,
                onValueChange = { gender = it },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.gender),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de registro
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { /* Manejar registro */ },
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                CustomText(data = TextData(text = stringResource(id = R.string.register)))
            }
        }
    }
}
