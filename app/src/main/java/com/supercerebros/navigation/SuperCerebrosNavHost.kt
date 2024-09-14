package com.supercerebros.navigation

import ChildOptionsMenuScreen
import SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.supercerebros.R
import com.supercerebros.screens.ChildRegistrationScreen
import com.supercerebros.screens.LoginScreen
import com.supercerebros.screens.PasswordRecoveryScreen
import com.supercerebros.screens.RegistrationScreen
import com.supercerebros.screens.SuccessScreen
import com.supercerebros.screens.SuccessScreen2
import com.supercerebros.screens.TutorMenuScreen
import com.supercerebros.screens.UnderConstructionScreen
import com.supercerebros.screens.breathing.BreathingExerciseAnimationScreen
import com.supercerebros.screens.breathing.BreathingExerciseScreen
import com.supercerebros.screens.puzzle.PuzzleGameScreen
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
        composable("splashscreen") {
            SplashScreen()
        }

        composable("login") {
            LoginScreen(
                navController = navController,
                onLoginClick = { email, password ->
                    // Manejar login
                },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegistrationScreen(
                navController = navController,
                onBackClick = { navController.popBackStack() },
            )
        }

        composable("passwordRecovery") {
            PasswordRecoveryScreen(
                navController = navController,
                onSendCodeClick = { email ->

                },
                onVerifyCodeClick = {},
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("tutorMenu") {
            TutorMenuScreen(
                navController = navController,
                onRegisterChildClick = {
                    navController.navigate("registerChild")
                },
                onGlobalActivitiesClick = {
                    navController.navigate("construction")
                },
                onCalendarClick = {
                    navController.navigate("construction")
                },
                onResourcesAndTipsClick = {
                    navController.navigate("construction")
                },
                onAccountSettingsClick = {
                    navController.navigate("construction")
                },
                onSupportAndHelpClick = {
                    navController.navigate("construction")
                },
                onLogoutClick = {
                    // Aquí puedes manejar el cierre de sesión
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
                        navController = navController,
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

        composable("childMenu") {
            ChildOptionsMenuScreen(navController = navController)
        }

        composable("breathingExerciseScreen") {
            BreathingExerciseScreen(navController = navController)
        }

        composable(
            "breathingExerciseAnimationScreen/{fillDurationMillis}/{fillPauseMillis}/{emptyDurationMillis}/{emptyPauseMillis}/{repeatCount}",
            arguments = listOf(
                navArgument("fillDurationMillis") { type = NavType.IntType },
                navArgument("fillPauseMillis") { type = NavType.IntType },
                navArgument("emptyDurationMillis") { type = NavType.IntType },
                navArgument("emptyPauseMillis") { type = NavType.IntType },
                navArgument("repeatCount") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val fillDurationMillis = backStackEntry.arguments?.getInt("fillDurationMillis") ?: 6000
            val fillPauseMillis = backStackEntry.arguments?.getInt("fillPauseMillis") ?: 2000
            val emptyDurationMillis = backStackEntry.arguments?.getInt("emptyDurationMillis") ?: 4000
            val emptyPauseMillis = backStackEntry.arguments?.getInt("emptyPauseMillis") ?: 3000
            val repeatCount = backStackEntry.arguments?.getInt("repeatCount") ?: 3

            BreathingExerciseAnimationScreen(
                fillDurationMillis = fillDurationMillis,
                fillPauseMillis = fillPauseMillis,
                emptyDurationMillis = emptyDurationMillis,
                emptyPauseMillis = emptyPauseMillis,
                repeatCount = repeatCount,
                navController = navController
            )
        }

        composable("puzzleGameScreen") {
            val context = LocalContext.current
            PuzzleGameScreen(context = context, resourceId = R.drawable.eyes_screen)

        }

    }

        composable("puzzleGameScreen") {
            val context = LocalContext.current
            PuzzleGameScreen(context = context, resourceId = R.drawable.eyes_screen)
        }

        // Agregar SuccessScreen aquí
        composable("successScreen") {
            SuccessScreen(
                navController = navController
            )
        }
        composable("successScreen2") {
            SuccessScreen2(
                navController = navController

            )
        }
        composable("construction"){
            UnderConstructionScreen(
                navController=navController
            )
        }
    }
}


