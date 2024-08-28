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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreathingExerciseScreen(navController: NavController) {
    var inhaleDuration by remember { mutableIntStateOf(4) }
    var holdBreathDuration by remember { mutableIntStateOf(0) }
    var exhaleDuration by remember { mutableIntStateOf(4) }
    var pauseDuration by remember { mutableIntStateOf(0) }
    var totalDuration by remember { mutableIntStateOf(5) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Respiración Equilibrada") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp() // Navega hacia atrás
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    DurationSelector("Inhalar (segundos)", inhaleDuration, { if (inhaleDuration > 0) inhaleDuration-- }, { if (inhaleDuration < 60) inhaleDuration++ })
                    DurationSelector("Retener (segundos)", holdBreathDuration, { if (holdBreathDuration > 0) holdBreathDuration-- }, { if (holdBreathDuration < 60) holdBreathDuration++ })
                    DurationSelector("Exhalar (segundos)", exhaleDuration, { if (exhaleDuration > 0) exhaleDuration-- }, { if (exhaleDuration < 60) exhaleDuration++ })
                    DurationSelector("Pausa (segundos)", pauseDuration, { if (pauseDuration > 0) pauseDuration-- }, { if (pauseDuration < 60) pauseDuration++ })
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    TotalDurationSelector("Duración (minutos)", totalDuration, { if (totalDuration > 1) totalDuration-- }, { if (totalDuration < 60) totalDuration++ })
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Perfiles predefinidos", fontSize = 18.sp, fontWeight = FontWeight.Bold)

                    // Fila con 3 botones en cada fila, total 2 filas
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

            Button(
                onClick = {
                    val repeatCount = ((totalDuration * 60.0) / (inhaleDuration + holdBreathDuration + exhaleDuration + pauseDuration)).toInt()

                    navController.navigate(
                        "breathingExerciseAnimationScreen/${inhaleDuration * 1000}/" +
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

@Composable
fun ProfileButton(profileName: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(35.dp).padding(0.dp) // Define un tamaño fijo para cada botón
    ) {
        Text(profileName, fontSize = 12.sp)
    }
}

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BreathingExerciseScreen(navController = rememberNavController())
}
