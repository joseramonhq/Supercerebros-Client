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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.supercerebros.MyApplication
import com.supercerebros.ui.theme.SupercerebrosTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorMenuScreen(
    onRegisterChildClick: () -> Unit,
    onGlobalActivitiesClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onResourcesAndTipsClick: () -> Unit,
    onAccountSettingsClick: () -> Unit,
    onSupportAndHelpClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onBackClick: () -> Unit
) {
    // Acceder a la instancia de MyApplication
    val context = LocalContext.current
    val app = context.applicationContext as MyApplication

    // Obtener el usuario actual
    val currentUser = app.currentUser

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú Principal") },
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
            // Mostrar el nombre del usuario actual
            Text(
                text = "Bienvenido, ${currentUser?.firstName ?: "Usuario"}",
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
        )
    }
}
