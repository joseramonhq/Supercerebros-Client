package com.myapp.router

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myapp.screens.LoginScreen
import com.myapp.screens.RegistrationScreen

@Composable
fun NavGraph(startDestination: String = "login") {
    val navController = rememberNavController()

    NavHost(
        navController = navController, // Proporciona el NavController para gestionar la navegación
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()// Define la pantalla inicial al iniciar la aplicación.--> fillMaxSize soluciona un problema de transiciones en la navegación
    ) {
        composable("login") {
            LoginScreen(navController)
        }
        composable("register_user") {
            RegistrationScreen(navController)
        }
    }
}