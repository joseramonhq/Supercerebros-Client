package com.supercerebros.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.supercerebros.R
import com.supercerebros.ui.theme.SupercerebrosTheme

@Composable
fun UnderConstructionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)) // Fondo colorido y amigable
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pila de ojos graciosos
        Image(
            painter = painterResource(id = R.drawable.eyes_screen), // Asegúrate de tener esta imagen en res/drawable
            contentDescription = "Ojos graciosos observando",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape) // Forma circular para simular los ojos
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Texto gracioso de "En construcción"
        Text(
            text = "¡Ups! Estamos construyendo algo increíble aquí.",
            style = MaterialTheme.typography.titleSmall,
            color = Color(0xFF00796B),
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Vuelve pronto y no te preocupes, ¡nuestros ojos están en el trabajo!",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF00796B),
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnderConstructionScreenPreview() {
    SupercerebrosTheme {
        UnderConstructionScreen()
    }
}


