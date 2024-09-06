package com.supercerebros.screens.breathing

// Importaciones necesarias para la funcionalidad y diseño de la pantalla de animación de ejercicios de respiración.
import androidx.compose.animation.core.animateDpAsState // Para animar un valor de tipo Dp (densidad de píxeles).
import androidx.compose.animation.core.tween // Para definir una animación con una interpolación de tiempo específica.
import androidx.compose.foundation.background // Para agregar un color de fondo a un componente.
import androidx.compose.foundation.border // Para agregar un borde alrededor de un componente.
import androidx.compose.foundation.layout.Box // Un contenedor básico que puede contener un solo hijo y se puede usar para la composición.
import androidx.compose.foundation.layout.Column // Un contenedor que organiza sus hijos de manera vertical.
import androidx.compose.foundation.layout.fillMaxSize // Modificador que hace que un componente ocupe to do el espacio disponible.
import androidx.compose.foundation.layout.height // Modificador que establece la altura de un componente.
import androidx.compose.foundation.layout.padding // Modificador que agrega espacio alrededor de un componente.
import androidx.compose.foundation.layout.width // Modificador que establece el ancho de un componente.
import androidx.compose.material3.Button // Componente de botón que puede ser presionado por el usuario.
import androidx.compose.material3.Text // Componente de texto que muestra texto en la pantalla.
import androidx.compose.runtime.Composable // Anotación para funciones que definen la UI de manera declarativa.
import androidx.compose.runtime.LaunchedEffect // Efecto que se lanza cuando se inicia la composición; útil para tareas asincrónicas.
import androidx.compose.runtime.getValue // Para obtener el valor actual de un estado en la composición.
import androidx.compose.runtime.mutableIntStateOf // Para crear y recordar un estado mutable de tipo entero.
import androidx.compose.runtime.mutableStateOf // Para crear y recordar un estado mutable.
import androidx.compose.runtime.remember // Para recordar un estado o un objeto a través de recomposiciones.
import androidx.compose.runtime.rememberCoroutineScope // Para recordar el alcance de las corrutinas, útil para manejar tareas asíncronas.
import androidx.compose.runtime.setValue // Para actualizar el valor de un estado en la composición.
import androidx.compose.ui.Alignment // Para alinear componentes dentro de un contenedor.
import androidx.compose.ui.Modifier // Objeto que permite modificar la apariencia y el comportamiento de los componentes.
import androidx.compose.ui.graphics.Color // Clase para manejar y aplicar colores.
import androidx.compose.ui.platform.LocalContext // Para acceder al contexto actual de la aplicación.
import androidx.compose.ui.tooling.preview.Preview // Anotación para definir vistas previas de la UI en el editor.
import androidx.compose.ui.unit.dp // Unidad de medida para dimensiones, basada en la densidad de píxeles de la pantalla.
import androidx.compose.ui.unit.sp // Unidad de medida para tamaños de fuente, basada en puntos tipográficos.
import androidx.navigation.NavController // Controlador de navegación para manejar la navegación entre pantallas.
import com.supercerebros.api.RetrofitInstance // Instancia de Retrofit para manejar llamadas a la API.
import com.supercerebros.models.BreathingSession // Modelo de datos para una sesión de respiración.
import com.supercerebros.ui.theme.SupercerebrosTheme // Tema de la aplicación que define los estilos visuales.
import kotlinx.coroutines.delay // Función para pausar la ejecución de una corrutina por un tiempo específico.
import kotlinx.coroutines.launch // Función para lanzar una nueva corrutina.
import retrofit2.Call // Clase que representa una llamada a la API.

/**
 * Pantalla que muestra una animación para un ejercicio de respiración, diseñada para guiar a los niños
 * en un patrón de respiración controlada. La animación incluye fases de inhalación, retención, exhalación
 * y pausa, repetidas un tiempo específico.
 *
 * @param childId ID del niño, utilizado para identificar la sesión en la base de datos.
 * @param navController Controlador de navegación para moverse entre pantallas.
 * @param fillDurationMillis Duración del llenado en milisegundos. Valor por defecto: 6000 ms.
 * @param fillPauseMillis Pausa después de llenar en milisegundos. Valor por defecto: 2000 ms.
 * @param emptyDurationMillis Duración del vaciado en milisegundos. Valor por defecto: 4000 ms.
 * @param emptyPauseMillis Pausa después de vaciar en milisegundos. Valor por defecto: 3000 ms.
 * @param repeatCount Número de veces que se repite el ciclo completo de respiración. Valor por defecto: 3.
 */
@Composable
fun BreathingExerciseAnimationScreen(
    childId: String, // ID del niño, usado para identificar a qué niño pertenece esta sesión.
    navController: NavController, // Controlador de navegación, permite moverse entre pantallas.
    fillDurationMillis: Int = 6000, // Tiempo en milisegundos que tarda el "agua" en llenar el rectángulo.
    fillPauseMillis: Int = 2000, // Tiempo en milisegundos que la animación se detiene después de llenarse.
    emptyDurationMillis: Int = 4000, // Tiempo en milisegundos que tarda el "agua" en vaciarse.
    emptyPauseMillis: Int = 3000, // Tiempo en milisegundos que la animación se detiene después de vaciarse.
    repeatCount: Int = 3 // Número de veces que se repite el ciclo de inhalación y exhalación.
) {
    var countdown by remember { mutableIntStateOf(3) } // Estado para manejar la cuenta regresiva antes de empezar la animación.
    var isCountdownFinished by remember { mutableStateOf(false) } // Estado que indica si la cuenta regresiva ha terminado.
    var startAnimation by remember { mutableStateOf(false) } // Estado que controla si la animación debe comenzar.
    var isFilling by remember { mutableStateOf(true) } // Estado que indica si el rectángulo se está llenando o vaciando.
    var cycleCount by remember { mutableIntStateOf(0) } // Estado que lleva el conteo de los ciclos completados.
    var showText by remember { mutableStateOf(false) } // Estado que controla si se debe mostrar el texto "Fin" al finalizar la animación.
    var currentPhase by remember { mutableStateOf("Inhalar") } // Estado que indica la fase actual de la respiración (Inhalar, Exhalar, etc.).
    var elapsedTime by remember { mutableIntStateOf(0) } // Estado que lleva el tiempo transcurrido en la fase actual.
    val coroutineScope = rememberCoroutineScope() // Almacena el alcance de las corrutinas, necesario para lanzar tareas asíncronas.

    // Efecto que se lanza cuando se inicia la composición para manejar la cuenta regresiva.
    LaunchedEffect(Unit) {
        while (countdown > 0) { // Mientras la cuenta regresiva no haya llegado a cero.
            delay(1000L) // Espera un segundo.
            countdown-- // Reduce la cuenta regresiva en uno.
        }
        isCountdownFinished = true // Indica que la cuenta regresiva ha terminado.
        startAnimation = true // Activa la animación de respiración.
    }

    // Determina el color basado en la fase actual (Inhalar, Exhalar, etc.).
    val phaseColor = color(currentPhase)

    // Animación que controla la altura del "agua" en el rectángulo.
    val animatedHeight by animateDpAsState(
        targetValue = when {
            startAnimation && isFilling -> 500.dp // Si está llenando, la altura máxima es 500.dp.
            startAnimation && !isFilling -> 0.dp // Si está vaciando, la altura mínima es 0.dp.
            else -> 0.dp // Si no hay animación, la altura es 0.dp.
        },
        animationSpec = tween( // Configuración de la animación.
            durationMillis = if (isFilling) fillDurationMillis else emptyDurationMillis, // Duración de la animación dependiendo si se llena o se vacía.
            easing = { fraction ->
                // Interpolación para suavizar la animación.
                android.view.animation.AccelerateDecelerateInterpolator().getInterpolation(fraction)
            }
        ),
        finishedListener = { // Listener que se ejecuta cuando la animación termina.
            coroutineScope.launch {
                if (isFilling) { // Si estaba llenando.
                    elapsedTime = 0 // Reinicia el tiempo transcurrido.
                    currentPhase = "Mantener" // Cambia la fase a "Mantener" (retención de aire).
                    delay(fillPauseMillis.toLong()) // Pausa durante el tiempo definido después de llenar.
                    currentPhase = "Exhalar" // Cambia la fase a "Exhalar".
                    elapsedTime = 0 // Reinicia el tiempo transcurrido.
                    isFilling = false // Cambia el estado a vaciado.
                } else { // Si estaba vaciando.
                    elapsedTime = 0 // Reinicia el tiempo transcurrido.
                    currentPhase = "Pausa" // Cambia la fase a "Pausa".
                    delay(emptyPauseMillis.toLong()) // Pausa durante el tiempo definido después de vaciar.
                    cycleCount++ // Incrementa el conteo de ciclos completados.
                    if (cycleCount < repeatCount) { // Si no se han completado todos los ciclos.
                        currentPhase = "Inhalar" // Vuelve a la fase de inhalar.
                        elapsedTime = 0 // Reinicia el tiempo transcurrido.
                        isFilling = true // Cambia el estado a llenado.
                    } else { // Si todos los ciclos han sido completados.
                        startAnimation = false // Detiene la animación.
                        showText = true // Muestra el texto "Fin".
                        saveSession( // Llama a la función para guardar la sesión en el servidor.
                            childId, // ID del niño.
                            fillDurationMillis, // Duración del llenado.
                            fillPauseMillis, // Pausa después de llenar.
                            emptyDurationMillis, // Duración del vaciado.
                            emptyPauseMillis, // Pausa después de vaciar.
                            repeatCount, // Número de ciclos completados.
                            cycleCount >= repeatCount // Indica si la sesión se completó.
                        )
                    }
                }
            }
        }, label = "animateBreathing" // Etiqueta para identificar la animación.
    )

    // Efecto que actualiza el tiempo transcurrido en la fase actual.
    LaunchedEffect(startAnimation, currentPhase) {
        while (startAnimation) { // Mientras la animación esté activa.
            delay(1000L) // Espera un segundo.
            if (elapsedTime < when (currentPhase) { // Si el tiempo transcurrido es menor que el tiempo total de la fase actual.
                    "Inhalar" -> fillDurationMillis / 1000 // Duración en segundos de la fase de inhalar.
                    "Exhalar" -> emptyDurationMillis / 1000 // Duración en segundos de la fase de exhalar.
                    "Mantener" -> fillPauseMillis / 1000 // Duración en segundos de la fase de retención.
                    "Pausa" -> emptyPauseMillis / 1000 // Duración en segundos de la fase de pausa.
                    else -> 0
                } - 1) { // Resta 1 para no mostrar el último valor.
                elapsedTime++ // Incrementa el tiempo transcurrido.
            }
        }
    }

    // Definición de la UI de la pantalla.
    Box(
        contentAlignment = Alignment.Center, // Centra todo el contenido dentro del Box.
        modifier = Modifier.fillMaxSize() // Hace que el Box ocupe todo el espacio disponible en la pantalla.
    ) {
        if (!isCountdownFinished) { // Si la cuenta regresiva no ha terminado.
            Text(
                text = "$countdown", // Muestra el número actual de la cuenta regresiva.
                fontSize = 48.sp, // Tamaño de la fuente del texto de la cuenta regresiva.
                color = Color.Black // Color del texto de la cuenta regresiva.
            )
        } else { // Si la cuenta regresiva ha terminado.
            Column(
                horizontalAlignment = Alignment.CenterHorizontally // Alinea todos los elementos horizontalmente en el centro.
            ) {
                // Contenedor que representa el área donde se muestra la animación de llenado y vaciado.
                Box(
                    modifier = Modifier
                        .width(300.dp) // Define el ancho del contenedor.
                        .height(500.dp) // Define la altura del contenedor.
                        .border(2.dp, Color.Black) // Agrega un borde negro alrededor del contenedor.
                        .align(Alignment.CenterHorizontally) // Centra el contenedor horizontalmente.
                ) {
                    // Caja interna que se llena y vacía como si fuera agua.
                    Box(
                        modifier = Modifier
                            .width(300.dp) // Define el ancho del "agua".
                            .height(animatedHeight) // Altura del "agua", controlada por la animación.
                            .background(phaseColor) // Color del "agua", cambia según la fase.
                            .align(Alignment.BottomCenter) // Alinea el "agua" en la parte inferior del contenedor.
                    )
                }

                // Muestra la fase actual y el tiempo transcurrido.
                Text(
                    text = "$currentPhase - $elapsedTime s", // Muestra la fase y el tiempo transcurrido.
                    fontSize = 20.sp, // Tamaño de la fuente del texto.
                    color = Color.Black, // Color del texto.
                    modifier = Modifier.padding(top = 16.dp) // Espacio superior de 16.dp.
                )

                // Botón para salir de la pantalla y volver atrás.
                Button(
                    onClick = {
                        navController.popBackStack() // Vuelve a la pantalla anterior en la pila de navegación.
                    },
                    modifier = Modifier.padding(top = 16.dp) // Espacio superior de 16.dp.
                ) {
                    Text("Salir") // Texto del botón.
                }

                // Muestra el texto "Fin" si la animación ha terminado.
                showEnd(showText) // Llama a la función para mostrar el texto "Fin".
            }
        }
    }
}

/**
 * Devuelve un color basado en la fase actual del ejercicio de respiración.
 *
 * @param currentPhase La fase actual de la respiración (Inhalar, Mantener, Exhalar, Pausa).
 * @return El color asociado con la fase actual.
 */
fun color(currentPhase: String): Color {
    return when (currentPhase) {
        "Inhalar" -> Color(0xFFB2EBF2) // Azul claro durante la inhalación.
        "Mantener" -> Color(0xFF81C784) // Verde claro durante la retención de aire.
        "Exhalar" -> Color(0xFFFFCC80) // Naranja claro durante la exhalación.
        "Pausa" -> Color(0xFFB39DDB) // Púrpura claro durante la pausa.
        else -> Color.Transparent // Transparente por defecto.
    }
}

/**
 * Muestra el texto "Fin" al final de la animación.
 *
 * @param showText Booleano que indica si se debe mostrar el texto "Fin".
 */
@Composable
fun showEnd(showText: Boolean) {
    if (showText) { // Si el estado showText es verdadero.
        Text(
            text = "Fin", // Muestra el texto "Fin".
            fontSize = 24.sp, // Tamaño de la fuente del texto.
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, // Negrita para el texto.
            color = Color.Black, // Color negro para el texto.
            modifier = Modifier.padding(top = 16.dp) // Espacio superior de 16.dp.
        )
    }
}


/**
 * Guarda la sesión de respiración en el servidor.
 *
 * @param childId ID del niño para identificar a quién pertenece la sesión.
 * @param fillDurationMillis Duración del llenado en milisegundos.
 * @param fillPauseMillis Pausa después del llenado en milisegundos.
 * @param emptyDurationMillis Duración del vaciado en milisegundos.
 * @param emptyPauseMillis Pausa después del vaciado en milisegundos.
 * @param repeatCount Número de ciclos completados.
 * @param completed Booleano que indica si la sesión fue completada.
 */
fun saveSession(
    childId: String, // ID del niño para identificar a quién pertenece la sesión.
    fillDurationMillis: Int, // Duración del llenado en milisegundos.
    fillPauseMillis: Int, // Pausa después del llenado en milisegundos.
    emptyDurationMillis: Int, // Duración del vaciado en milisegundos.
    emptyPauseMillis: Int, // Pausa después del vaciado en milisegundos.
    repeatCount: Int, // Número de ciclos completados.
    completed: Boolean // Indica si la sesión fue completada o no.
) {
    // Crea una nueva instancia de BreathingSession con los parámetros proporcionados.
    val session = BreathingSession(
        childId = childId, // Asigna el ID del niño.
        fillDurationMillis = fillDurationMillis, // Asigna la duración del llenado.
        fillPauseMillis = fillPauseMillis, // Asigna la pausa después de llenar.
        emptyDurationMillis = emptyDurationMillis, // Asigna la duración del vaciado.
        emptyPauseMillis = emptyPauseMillis, // Asigna la pausa después de vaciar.
        repeatCount = repeatCount, // Asigna el número de ciclos completados.
        completed = completed // Asigna si la sesión fue completada.
    )

    // Llama al servicio de API para guardar la sesión.
    val call = RetrofitInstance.apiService.createSession(session)
    call.enqueue(object : retrofit2.Callback<Void> { // Maneja la respuesta de la llamada a la API.
        override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
            if (response.isSuccessful) {
                // Sesión guardada exitosamente.
            } else {
                // Maneja el error en caso de que la respuesta no sea exitosa.
            }
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            // Maneja el error en caso de que la llamada a la API falle.
        }
    })
}

// Función para previsualizar la pantalla en el editor de UI de Compose.
@Preview(showBackground = true)
@Composable
fun BreathingExerciseAnimationScreenPreview() {
    // Crea un controlador de navegación falso para la previsualización.
    val fakeNavController = NavController(context = LocalContext.current)
    // Aplica el tema de la aplicación para la previsualización.
    SupercerebrosTheme {
        // Llama a la función principal de la pantalla con valores de prueba.
        BreathingExerciseAnimationScreen(
            navController = fakeNavController, // Controlador de navegación falso.
            fillDurationMillis = 6000, // Duración de llenado de prueba.
            emptyDurationMillis = 4000, // Duración de vaciado de prueba.
            fillPauseMillis = 2000, // Pausa después de llenar de prueba.
            emptyPauseMillis = 3000, // Pausa después de vaciar de prueba.
            repeatCount = 3, // Número de ciclos de prueba.
            childId = null!! // Proporciona un ID de prueba (aquí es null para evitar errores en la vista previa).
        )
    }
}
