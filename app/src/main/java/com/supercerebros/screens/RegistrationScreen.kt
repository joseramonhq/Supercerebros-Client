package com.supercerebros.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.supercerebros.api.RetrofitInstance
import com.supercerebros.data.UserResponse
import com.supercerebros.models.User
import com.supercerebros.ui.theme.SupercerebrosTheme
import com.supercerebros.utils.validateBirthDate
import com.supercerebros.utils.validateConfirmPassword
import com.supercerebros.utils.validateDNI
import com.supercerebros.utils.validateEmail
import com.supercerebros.utils.validateLastName
import com.supercerebros.utils.validateName
import com.supercerebros.utils.validatePassword
import com.supercerebros.utils.validatePhoneNumber
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
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
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    fillFormForTesting(
        setName = { name = it },
        setLastName = { lastName = it },
        setBirthDate = { birthDate = it },
        setPhoneNumber = { phoneNumber = it },
        setDNI = { dni = it },
        setEmail = { email = it },
        setPassword = { password = it },
        setConfirmPassword = { confirmPassword = it }
    )

    // Estados de error
    var nameError by remember { mutableStateOf<String?>(null) }
    var lastNameError by remember { mutableStateOf<String?>(null) }
    var birthDateError by remember { mutableStateOf<String?>(null) }
    var phoneNumberError by remember { mutableStateOf<String?>(null) }
    var dniError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    val dateDialogState = rememberMaterialDialogState()

    // Estilo de texto para los mensajes de error
    val errorTextStyle = TextStyle(
        color = MaterialTheme.colorScheme.error,
        fontSize = 12.sp // Cambia este valor para ajustar el tamaño del texto
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de tutor") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick?.invoke() ?: navController?.popBackStack() }) {
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
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                            nameError = validateName(it)
                        },
                        label = { Text("Nombre") },
                        isError = nameError != null,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    nameError?.let {
                        Text(
                            text = it,
                            style = errorTextStyle,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp) // Alinea el texto con el campo
                        )
                    }
                }
            }

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
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp) // Alinea el texto con el campo
                        )
                    }
                }
            }

            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = selectedDate,
                        onValueChange = { },
                        label = { Text("Fecha de nacimiento") },
                        isError = birthDateError != null,
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = { showDatePicker = !showDatePicker }) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Fecha de nacimiento"
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                    )
                    birthDateError?.let {
                        Text(
                            text = it,
                            style = errorTextStyle,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp) // Alinea el texto con el campo
                        )
                    }

                    if (showDatePicker) {
                        Dialog(
                            onDismissRequest = { showDatePicker = false },
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(y = 20.dp)
                                    .shadow(elevation = 6.dp)
                                    .background(MaterialTheme.colorScheme.surface)
                                    .padding(16.dp)
                            ) {
                                DatePicker(
                                    state = datePickerState,
                                    showModeToggle = false
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

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
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp) // Alinea el texto con el campo
                        )
                    }
                }
            }

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
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp) // Alinea el texto con el campo
                        )
                    }
                }
            }

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
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp) // Alinea el texto con el campo
                        )
                    }
                }
            }

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
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp) // Alinea el texto con el campo
                        )
                    }
                }
            }

            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            confirmPasswordError = validateConfirmPassword(password, it)
                        },
                        label = { Text("Confirmar Contraseña")
                        },
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
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp) // Alinea el texto con el campo
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Button(
                    onClick = {
                        if (validateForm(
                                nameError, lastNameError, birthDateError,
                                phoneNumberError, dniError, emailError,
                                passwordError, confirmPasswordError
                            )
                        ) {
                            val user = User(
                                role = "Tutor",
                                firstName = name,
                                lastName = lastName,
                                email = email,
                                password = password,
                                phone = phoneNumber,
                                birthDate = birthDate,
                                dni = dni,
                                gender = null,
                                medicalInfo = null,
                                parentId = null,
                                childrenIds = null,
                                fileIds = null,
                                id = null,
                                registrationDate = null,
                                createdAt = null,
                                updatedAt = null,

                            )
                            registerUser(user) {
                                // Acción a realizar cuando el registro es exitoso
                                navController?.navigate("successScreen")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrarse")
                }
            }

        }
    }

    // Material Dialog Date Picker
    MaterialDialog(dialogState = dateDialogState) {
        datepicker { date ->
            birthDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
            birthDateError = validateBirthDate(birthDate)
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

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

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
    ).all { it == null }
}

fun registerUser(user: User, onRegisterSuccess: () -> Unit) {
    Log.i("RegisterScreen", "Datos del usuario que se enviarán: $user")

    val apiService = RetrofitInstance.apiService

    apiService.registerUser(user).enqueue(object : Callback<UserResponse> {
        override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
            if (response.isSuccessful) {
                Log.i("RegisterScreen", "Registro exitoso: ${response.body()?.id}")
                onRegisterSuccess()
            } else {
                Log.e("RegisterScreen", "Error en el registro: ${response.message()} - ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            Log.e("RegisterScreen", "Error de red: ${t.message}")
        }
    })
}
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
    val testBirthDate = "15/08/1990" // Fecha original
    val serverFormattedBirthDate = convertToServerDateFormat(testBirthDate) // Fecha en formato esperado por el servidor
    val testPhoneNumber = "+34912345678" // Número de teléfono válido
    val testDNI = "20199257J" // DNI válido
    val testEmail = "juan.perez@example.com" // Correo electrónico válido
    val testPassword = "P@ssw0rd123" // Contraseña que pasa todas las validaciones
    val testConfirmPassword = testPassword // Confirmación que coincide con la contraseña

    setName(testName)
    setLastName(testLastName)
    setBirthDate(serverFormattedBirthDate ?: "") // Asegúrate de que no sea nulo
    setPhoneNumber(testPhoneNumber)
    setDNI(testDNI)
    setEmail(testEmail)
    setPassword(testPassword)
    setConfirmPassword(testConfirmPassword)
}
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

