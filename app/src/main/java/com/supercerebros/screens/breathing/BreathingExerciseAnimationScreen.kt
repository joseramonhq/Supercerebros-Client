package com.supercerebros.screens.breathing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.supercerebros.ui.theme.SupercerebrosTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BreathingExerciseAnimationScreen(
    navController: NavController,       // Añade NavController como parámetro
    fillDurationMillis: Int = 6000,    // Duración para llenar en milisegundos
    fillPauseMillis: Int = 2000,       // Pausa después de llenarse
    emptyDurationMillis: Int = 4000,   // Duración para vaciar en milisegundos
    emptyPauseMillis: Int = 3000,      // Pausa después de vaciarse
    repeatCount: Int = 3               // Número de ciclos de llenar/vaciar
) {
    var countdown by remember { mutableStateOf(3) }
    var isCountdownFinished by remember { mutableStateOf(false) }
    var startAnimation by remember { mutableStateOf(false) }
    var isFilling by remember { mutableStateOf(true) }
    var cycleCount by remember { mutableStateOf(0) }
    var showText by remember { mutableStateOf(false) }
    var currentPhase by remember { mutableStateOf("Inhalar") }
    var elapsedTime by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    // Iniciar la cuenta atrás
    LaunchedEffect(Unit) {
        while (countdown > 0) {
            delay(1000L)
            countdown--
        }
        isCountdownFinished = true
        startAnimation = true
    }

    // Determina el color basado en la fase actual
    val phaseColor = when (currentPhase) {
        "Inhalar" -> Color(0xFFB2EBF2) // Light Blue
        "Mantener" -> Color(0xFF81C784) // Light Green
        "Exhalar" -> Color(0xFFFFCC80) // Light Orange
        "Pausa" -> Color(0xFFB39DDB) // Light Purple
        else -> Color.Transparent
    }

    // Animación de la altura del "agua" en el rectángulo con una interpolación suave
    val animatedHeight by animateDpAsState(
        targetValue = when {
            startAnimation && isFilling -> 500.dp // Llenar
            startAnimation && !isFilling -> 0.dp // Vaciar
            else -> 0.dp
        },
        animationSpec = tween(
            durationMillis = if (isFilling) fillDurationMillis else emptyDurationMillis, // Duración de llenado o vaciado
            easing = { fraction ->
                android.view.animation.AccelerateDecelerateInterpolator().getInterpolation(fraction)
            }
        ),
        finishedListener = {
            coroutineScope.launch {
                if (isFilling) {
                    elapsedTime = 0 // Reinicia el temporizador al cambiar de fase
                    currentPhase = "Mantener"
                    delay(fillPauseMillis.toLong()) // Pausa después de llenarse
                    currentPhase = "Exhalar"
                    elapsedTime = 0
                    isFilling = false // Comienza el vaciado
                } else {
                    elapsedTime = 0 // Reinicia el temporizador al cambiar de fase
                    currentPhase = "Pausa"
                    delay(emptyPauseMillis.toLong()) // Pausa después de vaciarse
                    cycleCount++
                    if (cycleCount < repeatCount) {
                        currentPhase = "Inhalar"
                        elapsedTime = 0
                        isFilling = true // Vuelve a llenar
                    } else {
                        startAnimation = false
                        showText = true // Muestra el texto "Fin"
                    }
                }
            }
        }
    )

    // Temporizador para actualizar el tiempo transcurrido
    LaunchedEffect(startAnimation, currentPhase) {
        while (startAnimation) {
            delay(1000L)
            if (elapsedTime < when (currentPhase) {
                    "Inhalar" -> fillDurationMillis / 1000
                    "Exhalar" -> emptyDurationMillis / 1000
                    "Mantener" -> fillPauseMillis / 1000
                    "Pausa" -> emptyPauseMillis / 1000
                    else -> 0
                } - 1) { // Resta 1 para que no muestre el último valor
                elapsedTime++
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (!isCountdownFinished) {
            // Mostrar la cuenta atrás
            Text(
                text = "$countdown",
                fontSize = 48.sp,
                color = Color.Black
            )
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Contenedor del rectángulo con borde
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(500.dp)
                        .border(2.dp, Color.Black) // Borde del rectángulo
                        .align(Alignment.CenterHorizontally) // Alineación al centro
                ) {
                    // Caja que se llenará y vaciará con "agua"
                    Box(
                        modifier = Modifier
                            .width(300.dp) // Ancho fijo
                            .height(animatedHeight) // Altura animada
                            .background(phaseColor) // Color del "agua" basado en la fase
                            .align(Alignment.BottomCenter) // Empieza desde abajo
                    )
                }

                // Mostrar la fase actual y el tiempo transcurrido
                Text(
                    text = "$currentPhase - $elapsedTime s",
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp)
                )

                // Botón de salir
                Button(
                    onClick = {
                        navController.popBackStack() // Volver a la pantalla anterior
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Salir")
                }

                // Mostrar el texto "Fin" al final de la animación
                if (showText) {
                    Text(
                        text = "Fin",
                        fontSize = 24.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimatedRectangleScreen() {
    // Reemplaza con un NavController ficticio o de prueba si es necesario
    val fakeNavController = NavController(context = LocalContext.current)
    SupercerebrosTheme {
        BreathingExerciseAnimationScreen(
            navController = fakeNavController,
            fillDurationMillis = 6000,
            emptyDurationMillis = 4000,
            fillPauseMillis = 2000,
            emptyPauseMillis = 3000,
            repeatCount = 3
        )
    }
}
