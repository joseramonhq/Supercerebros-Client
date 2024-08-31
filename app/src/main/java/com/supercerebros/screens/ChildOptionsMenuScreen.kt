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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.supercerebros.MyApplication
import com.supercerebros.components.ExitConfirmationModal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildOptionsMenuScreen(navController: NavHostController? = null) {
    val context = LocalContext.current
    val app = context.applicationContext as MyApplication
    val currentChild = app.currentChild
    var showExitConfirmation by remember { mutableStateOf(false) } // Estado para mostrar el modal
    if (currentChild == null) {
        Text(
            text = "Error: Usuario no autenticado",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        return
    }
    val childName = currentChild.fullName
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Juegos", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = {
                        showExitConfirmation = true // Muestra el modal de confirmación de salida
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retroceder"
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenido, $childName",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    // Acción al hacer clic en el botón Respira!!!
                    navController?.navigate("breathingExerciseScreen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp) // Espacio entre los botones
            ) {
                Text("Respira!!!")
            }

            Button(
                onClick = {
                    // Acción al hacer clic en el botón Puzzle
                    navController?.navigate("puzzleGameScreen")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Puzzle")
            }
        }

        // Mostrar el modal de confirmación de salida si showExitConfirmation es true
        if (showExitConfirmation) {
            ExitConfirmationModal(
                onConfirm = {
                    showExitConfirmation = false // Ocultar el modal
                    navController?.navigateUp() // Navegar hacia atrás si se confirma la salida
                },
                onDismiss = {
                    showExitConfirmation = false // Ocultar el modal si se cancela la salida
                }
            )
        }
    }
}
