package com.supercerebros.screens.breathing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.supercerebros.MyApplication

/**
 * Pantalla principal para configurar y comenzar un ejercicio de respiración equilibrada.
 * Permite al usuario seleccionar la duración de las fases de respiración y elegir entre perfiles predefinidos.
 *
 * @param navController Controlador de navegación para manejar la navegación entre pantallas.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreathingExerciseScreen(navController: NavController) {
    // Variables de estado para almacenar la duración de las diferentes fases de respiración.
    var inhaleDuration by remember { mutableIntStateOf(4) } // Duración de la inhalación en segundos.
    var holdBreathDuration by remember { mutableIntStateOf(0) } // Duración de la retención de la respiración en segundos.
    var exhaleDuration by remember { mutableIntStateOf(4) } // Duración de la exhalación en segundos.
    var pauseDuration by remember { mutableIntStateOf(0) } // Duración de la pausa entre ciclos en segundos.
    var totalDuration by remember { mutableIntStateOf(5) } // Duración total del ejercicio en minutos.

    val context = LocalContext.current
    val app = context.applicationContext as MyApplication
    val currentChild = app.currentChild

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Respiración Equilibrada") }, // Título en la barra superior.
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp() // Navega hacia atrás en la pila de navegación.
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
            horizontalAlignment = Alignment.CenterHorizontally, // Alinea el contenido horizontalmente al centro.
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espacia los elementos con 16 dp de separación.
        ) {
            // Tarjeta para seleccionar la duración de las fases de respiración.
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    DurationSelector(
                        label = "Inhalar (segundos)",
                        value = inhaleDuration,
                        onDecrease = { if (inhaleDuration > 0) inhaleDuration-- },
                        onIncrease = { if (inhaleDuration < 60) inhaleDuration++ }
                    )
                    DurationSelector(
                        label = "Retener (segundos)",
                        value = holdBreathDuration,
                        onDecrease = { if (holdBreathDuration > 0) holdBreathDuration-- },
                        onIncrease = { if (holdBreathDuration < 60) holdBreathDuration++ }
                    )
                    DurationSelector(
                        label = "Exhalar (segundos)",
                        value = exhaleDuration,
                        onDecrease = { if (exhaleDuration > 0) exhaleDuration-- },
                        onIncrease = { if (exhaleDuration < 60) exhaleDuration++ }
                    )
                    DurationSelector(
                        label = "Pausa (segundos)",
                        value = pauseDuration,
                        onDecrease = { if (pauseDuration > 0) pauseDuration-- },
                        onIncrease = { if (pauseDuration < 60) pauseDuration++ }
                    )
                }
            }

            // Tarjeta para seleccionar la duración total del ejercicio.
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    TotalDurationSelector(
                        label = "Duración (minutos)",
                        duration = totalDuration,
                        onDecrease = { if (totalDuration > 1) totalDuration-- },
                        onIncrease = { if (totalDuration < 60) totalDuration++ }
                    )
                }
            }

            // Tarjeta para seleccionar perfiles predefinidos de respiración.
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Perfiles predefinidos", fontSize = 18.sp, fontWeight = FontWeight.Bold)

                    // Primera fila de botones de perfiles predefinidos.
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ProfileButton("Relajación", onClick = {
                            inhaleDuration = 4; holdBreathDuration = 7; exhaleDuration = 8; pauseDuration = 2
                        })
                        ProfileButton("Alivio Estrés", onClick = {
                            inhaleDuration = 4; holdBreathDuration = 4; exhaleDuration = 4; pauseDuration = 2
                        })
                        ProfileButton("Energizante", onClick = {
                            inhaleDuration = 2; holdBreathDuration = 2; exhaleDuration = 4; pauseDuration = 2
                        })
                    }

                    // Segunda fila de botones de perfiles predefinidos.
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ProfileButton("Sueño", onClick = {
                            inhaleDuration = 5; holdBreathDuration = 3; exhaleDuration = 5; pauseDuration = 2
                        })
                        ProfileButton("Calma Rápida", onClick = {
                            inhaleDuration = 3; holdBreathDuration = 3; exhaleDuration = 6; pauseDuration = 1
                        })
                        ProfileButton("Meditación", onClick = {
                            inhaleDuration = 6; holdBreathDuration = 4; exhaleDuration = 8; pauseDuration = 3
                        })
                    }
                }
            }

            // Botón para comenzar el ejercicio de respiración.
            Button(
                onClick = {
                    val repeatCount = ((totalDuration * 60.0) / (inhaleDuration + holdBreathDuration + exhaleDuration + pauseDuration)).toInt()

                    navController.navigate(
                        "breathingExerciseAnimationScreen/${currentChild?.id}/${inhaleDuration * 1000}/" +
                                "${holdBreathDuration * 1000}/" +
                                "${exhaleDuration * 1000}/" +
                                "${pauseDuration * 1000}/" +
                                "$repeatCount"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Comenzar")
            }
        }
    }
}

/**
 * Componente que representa un botón para seleccionar un perfil predefinido de respiración.
 *
 * @param profileName Nombre del perfil que se muestra en el botón.
 * @param onClick Acción que se ejecuta cuando el botón es presionado.
 */
@Composable
fun ProfileButton(profileName: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(35.dp)
            .padding(0.dp) // Define un tamaño fijo para cada botón.
    ) {
        Text(profileName, fontSize = 12.sp)
    }
}

/**
 * Componente que permite seleccionar la duración de una fase específica de respiración.
 *
 * @param label Etiqueta que describe la fase (Inhalar, Retener, Exhalar, Pausa).
 * @param value Valor actual de la duración de la fase en segundos.
 * @param onDecrease Acción que se ejecuta al disminuir la duración.
 * @param onIncrease Acción que se ejecuta al aumentar la duración.
 */
@Composable
fun DurationSelector(label: String, value: Int, onDecrease: () -> Unit, onIncrease: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onDecrease) {
                Text("-")
            }
            Text("$value s", fontSize = 18.sp)
            IconButton(onClick = onIncrease) {
                Text("+")
            }
        }
    }
}

/**
 * Componente que permite seleccionar la duración total del ejercicio en minutos.
 *
 * @param label Etiqueta que describe el selector de duración total.
 * @param duration Valor actual de la duración total en minutos.
 * @param onDecrease Acción que se ejecuta al disminuir la duración.
 * @param onIncrease Acción que se ejecuta al aumentar la duración.
 */
@Composable
fun TotalDurationSelector(label: String, duration: Int, onDecrease: () -> Unit, onIncrease: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onDecrease) {
                Text("-")
            }
            Text("$duration min", fontSize = 18.sp)
            IconButton(onClick = onIncrease) {
                Text("+")
            }
        }
    }
}

/**
 * Vista previa de la pantalla de ejercicio de respiración en el editor.
 */
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BreathingExerciseScreen(navController = rememberNavController())
}
