package com.supercerebros.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.supercerebros.ui.theme.SupercerebrosTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(navController: NavHostController? = null) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Juegos", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController?.navigateUp() // Navega hacia atrás
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
            Button(
                onClick = {
                    // Acción al hacer clic en el botón Juegos
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Juegos")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamesScreenPreview() {
    SupercerebrosTheme {
        GamesScreen()
    }
}


