package com.supercerebros

import SplashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.supercerebros.navigation.SuperCerebrosNavHost
import com.supercerebros.ui.theme.SupercerebrosTheme
import kotlinx.coroutines.delay

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SupercerebrosTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    var showSplashScreen by remember { mutableStateOf(true) }

    // Controlar el tiempo de visualización del SplashScreen
    LaunchedEffect(Unit) {
        delay(3000L) // Duración del SplashScreen en milisegundos
        showSplashScreen = false
    }

    if (showSplashScreen) {
        SplashScreen()
    } else {
        AppNavigation()
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    SuperCerebrosNavHost(navController = navController)
}
