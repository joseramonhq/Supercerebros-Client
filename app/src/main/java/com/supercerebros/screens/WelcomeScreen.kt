package com.supercerebros.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.supercerebros.ui.theme.SupercerebrosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SupercerebrosTheme {
                WelcomeScreen(
                    onSignUpClick = { /* TODO: Navigate to Sign Up screen */ },
                    onLoginClick = { /* TODO: Navigate to Login screen */ }
                )
            }
        }
    }
}

@Composable
fun WelcomeScreen(
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text
            = "¡Bienvenido!",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 24.dp) // Use named argument for padding

        )
        Text(
            text = "¿Ya tienes una cuenta? Inicia sesión o regístrate para comenzar.",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 24.dp) // Use named argument for padding

        )
        Button(
            onClick = onSignUpClick,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {  // Opening brace for Button content
            Text(text = "Registrarse")
        }  // Closing brace for Button content
        Button(
            onClick = onLoginClick
        ) { // Opening brace for Button content
            Text(text = "Iniciar Sesión")
        } // Closing brace for Button content
    }
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    SupercerebrosTheme {
        WelcomeScreen(
            onSignUpClick = {},
            onLoginClick = {}
        )
    }
}
