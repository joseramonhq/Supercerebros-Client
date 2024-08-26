package com.supercerebros.navigation

import SplashScreen
import TutorMenuScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.supercerebros.screens.ChildRegistrationScreen
import com.supercerebros.screens.LoginScreen
import com.supercerebros.screens.PasswordRecoveryScreen
import com.supercerebros.screens.RegistrationScreen
import com.supercerebros.screens.registerChild

@Composable
fun SuperCerebrosNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("splashscreen"){
            SplashScreen()
        }

        composable("login") {
            LoginScreen(
                navController = navController, // Pasar navController aquí
                onLoginClick = { email, password ->
                    // Manejar login
                },
                onRegisterClick = {navController.navigate("register")},
              //  onBackClick = { navController.popBackStack() }
            )
        }


        composable("register") {
            RegistrationScreen(
                onBackClick = { navController.popBackStack() },
            )
        }

        composable("passwordRecovery") {
            PasswordRecoveryScreen(
                onSendCodeClick = { email ->
                    // Lógica para enviar el código de recuperación
                },
                onVerifyCodeClick = {},
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("tutorMenu") {
            TutorMenuScreen(
                onRegisterChildClick = {
                    navController.navigate("registerChild")
                },
                onGlobalActivitiesClick = {
                    navController.navigate("globalActivities")
                },
                onCalendarClick = {
                    navController.navigate("calendar")
                },
                onResourcesAndTipsClick = {
                    navController.navigate("resourcesAndTips")
                },
                onAccountSettingsClick = {
                    navController.navigate("accountSettings")
                },
                onSupportAndHelpClick = {
                    navController.navigate("supportAndHelp")
                },
                onLogoutClick = {
                    // Aquí puedes manejar el cierre de sesión, como volver a la pantalla de inicio
                    navController.navigate("welcome") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("registerChild") {
            ChildRegistrationScreen(
                onRegisterClick = { child ->
                    registerChild(
                        child = child,
                        onSuccess = {
                            // Acción a realizar cuando el registro es exitoso
                            navController.navigate("successScreen")
                        }
                    )
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}



