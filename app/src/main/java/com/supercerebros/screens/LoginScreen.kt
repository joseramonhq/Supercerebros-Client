package com.supercerebros.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.supercerebros.MyApplication
import com.supercerebros.R
import com.supercerebros.api.RetrofitInstance
import com.supercerebros.data.LoginRequest
import com.supercerebros.data.LoginResponse
import com.supercerebros.ui.theme.SupercerebrosTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.login)) },
                actions = {
                    Text(
                        text = stringResource(R.string.sign_up),
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable(onClick = onRegisterClick)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(stringResource(R.string.email)) },
                modifier = Modifier.padding(bottom = 16.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text(stringResource(id = R.string.password)) },
                modifier = Modifier.padding(bottom = 16.dp),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )

            Text(
                text = stringResource(R.string.forgoten_password),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable {
                        navController.navigate("passwordRecovery")
                    }
            )

            Button(
                onClick = { onLoginClick(navController,email.value, password.value) },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Iniciar Sesión")
            }
        }
    }
}

fun onLoginClick(navController: NavController, email: String, password: String) {
    val loginRequest = LoginRequest(email, password)

    CoroutineScope(Dispatchers.IO).launch {
        val call = RetrofitInstance.authService.login(loginRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    val app = navController.context.applicationContext as MyApplication

                    loginResponse?.user?.let {
                        println("Usuario recibido: $it")
                        app.login(it)
                    } ?: run {
                        println("El usuario recibido es nulo")
                    }

                    navController.navigate("tutorMenu")
                } else {
                    println("Error en el login: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                println("Error de red o excepción: ${t.message}")
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    SupercerebrosTheme {
        LoginScreen(
            navController = navController,
            onLoginClick = { email, password ->
                onLoginClick(navController, email, password)
            },
            onRegisterClick = {}
        )
    }
}
