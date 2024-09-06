package com.supercerebros.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.supercerebros.api.RetrofitInstance
import com.supercerebros.data.UserResponse
import com.supercerebros.models.User
import com.supercerebros.ui.theme.SupercerebrosTheme
import com.supercerebros.utils.validateConfirmPassword
import com.supercerebros.utils.validateDNI
import com.supercerebros.utils.validateEmail
import com.supercerebros.utils.validateLastName
import com.supercerebros.utils.validateName
import com.supercerebros.utils.validatePassword
import com.supercerebros.utils.validatePhoneNumber
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavHostController? = null,
    onBackClick: (() -> Unit)? = null
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var birthDateState by remember { mutableStateOf(TextFieldValue("")) } // Aquí almacenamos la fecha de nacimiento como texto
    var birthDate by remember { mutableStateOf("") } // Aquí almacenamos la fecha formateada para el servidor

    // Estados de error
    var firstNameError by remember { mutableStateOf<String?>(null) }
    var lastNameError by remember { mutableStateOf<String?>(null) }
    var birthDateError by remember { mutableStateOf<String?>(null) }
    var phoneNumberError by remember { mutableStateOf<String?>(null) }
    var dniError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    val errorTextStyle = TextStyle(
        color = MaterialTheme.colorScheme.error,
        fontSize = 12.sp
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de tutor") },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClick?.invoke() ?: navController?.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nombre
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = {
                            firstName = it
                            firstNameError = validateName(it)
                        },
                        label = { Text("Nombre") },
                        isError = firstNameError != null,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    firstNameError?.let {
                        Text(
                            text = it,
                            style = errorTextStyle,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }
            }

            // Apellidos
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = {
                            lastName = it
                            lastNameError = validateLastName(it)
                        },
                        label = { Text("Apellidos") },
                        isError = lastNameError != null,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    lastNameError?.let {
                        Text(
                            text = it,
                            style = errorTextStyle,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }
            }

            // Fecha de nacimiento manual
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = birthDateState,
                        placeholder = { Text("dd/mm/yyyy") },  // Texto de marcador de posición
                        onValueChange = { input ->
                            // Filtrar solo dígitos y limitar la entrada a 8 dígitos
                            val digits = input.text.filter { it.isDigit() }.take(8)

                            // Formatear la fecha como "dd/MM/yyyy"
                            val formattedDate = when (digits.length) {
                                in 1..2 -> digits
                                in 3..4 -> "${digits.substring(0, 2)}/${digits.substring(2)}"
                                in 5..8 -> "${digits.substring(0, 2)}/${digits.substring(2, 4)}/${digits.substring(4)}"
                                else -> digits
                            }

                            // Calcular la nueva posición del cursor
                            val cursorPosition = formattedDate.length

                            // Actualizar el estado con el nuevo texto y posición del cursor
                            birthDateState = TextFieldValue(
                                text = formattedDate,
                                selection = TextRange(cursorPosition)
                            )

                            // Actualizar el valor de birthDate con el formato esperado por el servidor
                            if (formattedDate.length == 10) {
                                birthDate = convertDateToServerFormat(formattedDate) ?: ""
                            }
                        },
                        label = { Text("Fecha de Nacimiento") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),  // Tipo de teclado numérico
                    )
                    birthDateError?.let {
                        Text(
                            text = it,
                            style = errorTextStyle,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }
            }

            // Número de teléfono, DNI, correo, contraseña, etc...
            // ... (Resto de los campos como ya tienes en tu código)






            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Número de teléfono
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = {
                            phoneNumber = it
                            phoneNumberError = validatePhoneNumber(it)
                        },
                        label = { Text("Número de Teléfono") },
                        isError = phoneNumberError != null,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    phoneNumberError?.let {
                        Text(
                            text = it,
                            style = errorTextStyle,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }
            }

            // DNI
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = dni,
                        onValueChange = {
                            dni = it
                            dniError = validateDNI(it)
                        },
                        label = { Text("DNI") },
                        isError = dniError != null,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    dniError?.let {
                        Text(
                            text = it,
                            style = errorTextStyle,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }
            }

            // Correo Electrónico
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            emailError = validateEmail(it)
                        },
                        label = { Text("Correo Electrónico") },
                        isError = emailError != null,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    emailError?.let {
                        Text(
                            text = it,
                            style = errorTextStyle,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }
            }

            // Contraseña
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = validatePassword(it)
                        },
                        label = { Text("Contraseña") },
                        isError = passwordError != null,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    passwordError?.let {
                        Text(
                            text = it,
                            style = errorTextStyle,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }
            }

            // Confirmar Contraseña
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            confirmPasswordError = validateConfirmPassword(password, it)
                        },
                        label = { Text("Confirmar Contraseña") },
                        isError = confirmPasswordError != null,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    confirmPasswordError?.let {
                        Text(
                            text = it,
                            style = errorTextStyle,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            // Botón de registro
            // Botón de registro
            item {
                Button(
                    onClick = {
                        if (validateForm(
                                firstNameError, lastNameError, birthDateError,
                                phoneNumberError, dniError, emailError,
                                passwordError, confirmPasswordError
                            )
                        ) {
                            isLoading = true  // Iniciar la animación de carga

                            val user = User(
                                userOrChildType = "User",
                                id = null,
                                role = "Tutor",
                                firstName = firstName,
                                lastName = lastName,
                                email = email,
                                password = password,
                                phone = phoneNumber,
                                birthDate = birthDate,  // Aquí se usa la fecha formateada
                                dni = dni,
                                childrenIds = null,
                                registrationDate = null,
                                createdAt = null,
                                updatedAt = null,
                                active = true
                            )

                            registerUser(user) {
                                isLoading = false  // Detener la animación de carga
                                // Acción a realizar cuando el registro es exitoso
                                navController?.navigate("successScreen")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading  // Deshabilitar el botón mientras se carga
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary // Cambiar según tu tema
                        )
                    } else {
                        Text("Registrarse")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    val navController = rememberNavController()
    SupercerebrosTheme {
        RegistrationScreen(navController = navController)
    }
}

fun convertDateToServerFormat(dateString: String): String? {
    return try {
        // Formato original
        val originalFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        // Nuevo formato deseado
        val targetFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Analizar la fecha original
        val date = originalFormat.parse(dateString)
        // Devolver la fecha formateada
        date?.let { targetFormat.format(it) }
    } catch (e: Exception) {
        null // Devolver null si hay un error en el formato de la fecha
    }
}

// Convertir milisegundos en una fecha legible
fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

// Validar que todos los campos del formulario sean válidos
fun validateForm(
    nameError: String?,
    lastNameError: String?,
    birthDateError: String?,
    phoneNumberError: String?,
    dniError: String?,
    emailError: String?,
    passwordError: String?,
    confirmPasswordError: String?
): Boolean {
    return listOf(
        nameError,
        lastNameError,
        birthDateError,
        phoneNumberError,
        dniError,
        emailError,
        passwordError,
        confirmPasswordError
    ).all { it == null }  // Todos los errores deben ser nulos
}

// Función que maneja el registro de usuario en la API
fun registerUser(user: User, onRegisterSuccess: () -> Unit) {
    val apiService = RetrofitInstance.apiService

    // Llamada a la API para registrar el usuario
    apiService.registerUser(user).enqueue(object : Callback<UserResponse> {
        override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
            if (response.isSuccessful) {
                onRegisterSuccess()  // Si el registro es exitoso, se llama al callback
            } else {
                Log.e(
                    "RegisterScreen",
                    "Error en el registro: ${response.message()} - ${
                        response.errorBody()?.string()
                    }"
                )
            }
        }

        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            Log.e("RegisterScreen", "Error de red: ${t.message}")
        }
    })
}

// Rellenar el formulario automáticamente para pruebas
fun fillFormForTesting(
    setName: (String) -> Unit,
    setLastName: (String) -> Unit,
    setBirthDate: (String) -> Unit,
    setPhoneNumber: (String) -> Unit,
    setDNI: (String) -> Unit,
    setEmail: (String) -> Unit,
    setPassword: (String) -> Unit,
    setConfirmPassword: (String) -> Unit
) {
    val testName = "Juan"
    val testLastName = "Pérez"
    val testBirthDate = "15/08/1990"
    val serverFormattedBirthDate = convertToServerDateFormat(testBirthDate) ?: ""
    val testPhoneNumber = "+34912345678"
    val testDNI = "20199257J"
    val testEmail = "a@a.a"
    val testPassword = "12345Aa@"
    val testConfirmPassword = testPassword

    setName(testName)
    setLastName(testLastName)
    setBirthDate(serverFormattedBirthDate)
    setPhoneNumber(testPhoneNumber)
    setDNI(testDNI)
    setEmail(testEmail)
    setPassword(testPassword)
    setConfirmPassword(testConfirmPassword)
}

// Convertir la fecha al formato esperado por el servidor (yyyy-MM-dd)
fun convertToServerDateFormat(birthDate: String): String? {
    return try {
        val originalFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val targetFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = originalFormat.parse(birthDate)
        date?.let { targetFormat.format(it) }
    } catch (e: Exception) {
        null
    }
}
