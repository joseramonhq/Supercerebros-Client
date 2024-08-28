package com.supercerebros.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.supercerebros.MyApplication
import com.supercerebros.components.ExitConfirmationModal
import com.supercerebros.ui.theme.SupercerebrosTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorMenuScreen(
    navController: NavController,
    onRegisterChildClick: () -> Unit,
    onGlobalActivitiesClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onResourcesAndTipsClick: () -> Unit,
    onAccountSettingsClick: () -> Unit,
    onSupportAndHelpClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val app = context.applicationContext as MyApplication

    // Estado para controlar la visibilidad del modal
    var showExitConfirmation by remember { mutableStateOf(false) }

    // Obtener el usuario actual como UserOrChild.User
    val currentUser = app.currentUser

    // Manejar el caso cuando el usuario no esté autenticado o no sea un Tutor
    if (currentUser == null) {
        Text(
            text = "Error: Usuario no autenticado",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        return
    }

    val userName = currentUser.firstName

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú Principal") },
                navigationIcon = {
                    IconButton(onClick = { showExitConfirmation = true }) {
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
            // Mostrar el nombre del usuario actual
            Text(
                text = "Bienvenido, $userName",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = onRegisterChildClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(text = "Registrar Niño", fontSize = 18.sp)
            }
            Button(
                onClick = onGlobalActivitiesClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(text = "Actividades Globales", fontSize = 18.sp)
            }
            Button(
                onClick = onCalendarClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(text = "Calendario", fontSize = 18.sp)
            }
            Button(
                onClick = onResourcesAndTipsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(text = "Recursos y Tips", fontSize = 18.sp)
            }
            Button(
                onClick = onAccountSettingsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(text = "Configuración de Cuenta", fontSize = 18.sp)
            }
            Button(
                onClick = onSupportAndHelpClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(text = "Soporte y Ayuda", fontSize = 18.sp)
            }
            Button(
                onClick = onLogoutClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(text = "Salir", fontSize = 18.sp)
            }
        }

        // Mostrar el modal de confirmación de salida si showExitConfirmation es true
        if (showExitConfirmation) {
            ExitConfirmationModal(
                onConfirm = {
                    showExitConfirmation = false // Ocultar el modal
                    onBackClick() // Llamar la función onBackClick si se confirma la salida
                },
                onDismiss = {
                    showExitConfirmation = false // Ocultar el modal si se cancela la salida
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TutorMenuScreenPreview() {
    SupercerebrosTheme {
        TutorMenuScreen(
            onRegisterChildClick = {},
            onGlobalActivitiesClick = {},
            onCalendarClick = {},
            onResourcesAndTipsClick = {},
            onAccountSettingsClick = {},
            onSupportAndHelpClick = {},
            onLogoutClick = {},
            onBackClick = {},
            navController = rememberNavController()
        )
    }
}
