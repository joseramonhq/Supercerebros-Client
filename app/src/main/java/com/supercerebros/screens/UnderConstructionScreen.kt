package com.supercerebros.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.supercerebros.R
import com.supercerebros.ui.theme.SupercerebrosTheme

/**
 * Pantalla de "En construcción" que muestra un mensaje amigable, una imagen
 * y un botón para volver a la pantalla anterior.
 *
 * @param navController Controlador de navegación, usado para navegar hacia atrás.
 */
@Composable
fun UnderConstructionScreen(navController: NavHostController? = null) {
    // Composición principal de la pantalla: una columna centrada en la pantalla.
    Column(
        modifier = Modifier
            .fillMaxSize() // La columna llena toda la pantalla.
            .background(Color(0xFFE0F7FA)) // Fondo colorido y amigable.
            .padding(16.dp), // Añade relleno alrededor del contenido.
        verticalArrangement = Arrangement.Center, // Centra verticalmente el contenido.
        horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente el contenido.
    ) {
        // Imagen de los ojos graciosos que observarán al usuario.
        Image(
            painter = painterResource(id = R.drawable.eyes_screen), // Recurso de imagen.
            contentDescription = "Ojos graciosos observando", // Descripción de la imagen.
            contentScale = ContentScale.Crop, // Ajusta la imagen para rellenar su espacio.
            modifier = Modifier
                .size(250.dp) // Define el tamaño de la imagen.
                .clip(CircleShape) // Recorta la imagen en una forma circular.
        )

        // Espacio entre la imagen y el texto.
        Spacer(modifier = Modifier.height(24.dp))

        // Texto principal que informa al usuario que la pantalla está en construcción.
        Text(
            text = "¡Ups! Estamos construyendo algo increíble aquí.",
            style = MaterialTheme.typography.titleSmall, // Estilo del texto.
            color = Color(0xFF00796B), // Color del texto.
            modifier = Modifier.padding(8.dp) // Añade espacio alrededor del texto.
        )

        // Texto secundario que añade un toque humorístico.
        Text(
            text = "Vuelve pronto y no te preocupes, ¡nuestros ojos están en el trabajo!",
            style = MaterialTheme.typography.titleSmall, // Estilo del texto.
            color = Color(0xFF00796B), // Color del texto.
            fontSize = 16.sp, // Tamaño de la fuente.
            modifier = Modifier.padding(8.dp) // Añade espacio alrededor del texto.
        )

        // Espacio entre el texto y el botón.
        Spacer(modifier = Modifier.height(32.dp))

        // Botón que permite al usuario volver a la pantalla anterior.
        Button(
            onClick = {
                // Si el controlador de navegación no es nulo, navega hacia atrás.
                navController?.navigateUp()
            },
            modifier = Modifier.padding(top = 16.dp) // Espaciado superior para el botón.
        ) {
            // Texto dentro del botón.
            Text("Volver")
        }
    }
}

/**
 * Vista previa de la pantalla de "En construcción" para ser usada en el editor.
 */
@Preview(showBackground = true)
@Composable
fun UnderConstructionScreenPreview() {
    SupercerebrosTheme {
        UnderConstructionScreen()
    }
}


