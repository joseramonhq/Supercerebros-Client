
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun BreathingExerciseAnimationScreen(
    inhaleDuration: Int,
    holdBreathDuration: Int,
    exhaleDuration: Int,
    pauseDuration: Int,
    totalDuration: Int, // Duración total del ejercicio en minutos
    onFinish: () -> Unit // Callback para cuando el ejercicio termine
) {
    var currentPhase by remember { mutableStateOf(BreathingPhase.Inhale) }
    var timeRemaining by remember { mutableStateOf(inhaleDuration) }
    var animationProgress by remember { mutableStateOf(0f) }
    var isPaused by remember { mutableStateOf(false) }
    var showFinishedMessage by remember { mutableStateOf(false) }
    var cyclesCompleted by remember { mutableStateOf(0) }

    // Calcula el progreso de la animación en función de la fase y el tiempo restante
    val animatedProgress by animateFloatAsState(
        targetValue = when (currentPhase) {
            BreathingPhase.Inhale -> animationProgress
            BreathingPhase.Exhale -> 1 - animationProgress
            else -> if (currentPhase == BreathingPhase.Hold) 1f else 0f
        },
        animationSpec = tween(durationMillis = when (currentPhase) {
            BreathingPhase.Inhale -> inhaleDuration * 1000
            BreathingPhase.Exhale -> exhaleDuration * 1000
            else -> 0
        })
    )

    // Efecto lanzado para controlar el temporizador y la progresión de las fases
    LaunchedEffect(key1 = currentPhase, key2 = isPaused) {
        if (!isPaused) {
            while (timeRemaining > 0) {
                delay(1000L)
                timeRemaining--
                // Actualiza el progreso de la animación solo durante las fases de inhalación y exhalación
                if (currentPhase == BreathingPhase.Inhale || currentPhase == BreathingPhase.Exhale) {
                    animationProgress = 1 - (timeRemaining.toFloat() / when (currentPhase) {
                        BreathingPhase.Inhale -> inhaleDuration
                        BreathingPhase.Exhale -> exhaleDuration
                        else -> 1
                    })
                }
            }

            // Lógica para cambiar de fase cuando el tiempo se agota
            when (currentPhase) {
                BreathingPhase.Inhale -> {
                    currentPhase = BreathingPhase.Hold
                    timeRemaining = holdBreathDuration
                }
                BreathingPhase.Hold -> {
                    currentPhase = BreathingPhase.Exhale
                    timeRemaining = exhaleDuration
                }
                BreathingPhase.Exhale -> {
                    currentPhase = BreathingPhase.Pause
                    timeRemaining = pauseDuration
                }
                BreathingPhase.Pause -> {
                    cyclesCompleted++
                    // Verificar si el ejercicio ha terminado
                    if (cyclesCompleted >= totalDuration * 60 / (inhaleDuration + holdBreathDuration + exhaleDuration + pauseDuration)) {
                        showFinishedMessage = true
                        delay(3000L) // Mostrar el mensaje durante 3 segundos
                        onFinish() // Llamar al callback de finalización
                    } else {
                        // Si no ha terminado, reiniciar el ciclo
                        currentPhase = BreathingPhase.Inhale
                        timeRemaining = inhaleDuration
                        animationProgress = 0f
                    }
                }
            }
        }
    }

    // Diseño de la interfaz de usuario
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
                .background(Color.Gray.copy(alpha = 0.2f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(animatedProgress)
                    .background(
                        when (currentPhase) {
                            BreathingPhase.Inhale -> Color(0xFFB2EBF2)
                            BreathingPhase.Hold -> Color(0xFF81C784)
                            BreathingPhase.Exhale -> Color(0xFFFFCC80)
                            BreathingPhase.Pause -> Color(0xFFEEEEEE)
                        }
                    )
            )
        }

        Text(
            text = "$timeRemaining s",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = when (currentPhase) {
                BreathingPhase.Inhale -> Color(0xFFB2EBF2)
                BreathingPhase.Hold -> Color(0xFF81C784)
                BreathingPhase.Exhale -> Color(0xFFFFCC80)
                BreathingPhase.Pause -> Color(0xFFEEEEEE)
            }
        )
        Text(
            text = currentPhase.label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { isPaused = !isPaused }) {
                Text(if (isPaused) "▶️" else "⏸️")
            }
            IconButton(onClick = { onFinish() }) {
                Text("⏹")
            }
        }

        AnimatedVisibility(
            visible = showFinishedMessage,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = "¡Excelente trabajo! Sigue respirando y cuidándote.",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

enum class BreathingPhase(val label: String) {
    Inhale("Inhalando"),
    Hold("Reteniendo"),
    Exhale("Exhalando"),
    Pause("Pausa")
}

@Preview(showBackground = true)
@Composable
fun BreathingExerciseAnimationScreenPreview() {
    BreathingExerciseAnimationScreen(4, 2, 4, 2, totalDuration = 1) {}
}
