package com.supercerebros.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.supercerebros.MyApplication
import com.supercerebros.api.RetrofitInstance
import com.supercerebros.data.ChildResponse
import com.supercerebros.models.Child
import com.supercerebros.ui.theme.SupercerebrosTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildRegistrationScreen(
    navController: NavHostController? = null,
    onRegisterClick: (Child) -> Unit,  // Callback para manejar el registro del niño
    onBackClick: () -> Unit  // Callback para manejar la acción de retroceso
) {
    // Obtener la instancia de la aplicación actual
    val context = LocalContext.current
    val app = context.applicationContext as MyApplication
    // Obtener el usuario actual registrado en la aplicación
    val currentUser = app.currentUser

    // Definición de variables de estado para los campos de entrada, inicializados con valores predeterminados
    var fullName by remember { mutableStateOf("José Pérez") }  // Nombre completo del niño
    val genderOptions = listOf("Niño", "Niña", "No contesto")  // Opciones de género
    var selectedGender by remember { mutableStateOf(genderOptions[0]) }  // Género seleccionado
    var email by remember { mutableStateOf("jose@gmail.com") }  // Email del niño
    var password by remember { mutableStateOf("12345Aa@") }  // Contraseña del niño
    var medicalInfo by remember { mutableStateOf("Sin alergias conocidas") }  // Información médica del niño
   // var isLoading by remember { mutableStateOf(false) }  // Estado de carga para mostrar la animación de progreso

    Scaffold(
        topBar = {
            // Definición de la barra superior (AppBar)
            TopAppBar(
                title = { Text("Registrar Niño") },  // Título de la pantalla
                navigationIcon = {
                    // Botón de retroceso en la barra superior
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                },
                actions = {
                    // Mostrar el nombre del usuario actual en la barra superior si existe
                    currentUser?.let {
                        Text(
                            text = "Usuario: ${currentUser.firstName}",
                            modifier = Modifier.padding(end = 16.dp),
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // Cuerpo principal de la pantalla dentro de un LazyColumn para permitir desplazamiento
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Campo de entrada para el nombre completo
            item {
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Nombre y apellidos") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            // Campo de entrada para la fecha de nacimiento
            item {
                var birthDateState by remember { mutableStateOf(TextFieldValue("")) }

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
                    },
                    label = { Text("Fecha de Nacimiento") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),  // Tipo de teclado numérico
                )
            }
            // Selección de género utilizando RadioButton
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(top = 12.dp)
                ) {
                    // Etiqueta para la selección de género
                    Text(
                        text = "Género",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .background(Color.White)
                            .padding(horizontal = 4.dp)
                            .align(Alignment.TopStart)
                    )
                    // RadioButtons para la selección de género
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                    ) {
                        genderOptions.forEach { gender ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { selectedGender = gender }
                            ) {
                                RadioButton(
                                    selected = selectedGender == gender,
                                    onClick = { selectedGender = gender }
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = gender)
                            }
                        }
                    }
                }
            }

            // Campo de entrada para el correo electrónico
            item {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            // Campo de entrada para la contraseña
            item {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),  // Enmascarar la entrada
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
            // Campo de entrada para la información médica
            item {
                OutlinedTextField(
                    value = medicalInfo,
                    onValueChange = { medicalInfo = it },
                    label = { Text("Información Médica") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            // Espaciador para agregar espacio entre los elementos
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            // Botón de registro
            item {
                Button(
                    onClick = {
                       // isLoading = true  // Iniciar la animación de carga
                        val child = Child(
                            userOrChildType = "Child",
                            id = null,  // El ID será asignado por el servidor
                            fullName = fullName,
                            birthDate = "2015-09-15",  // Asegúrate de que está en el formato correcto "yyyy-MM-dd"
                            gender = selectedGender,
                            email = email,
                            password = password,
                            medicalInfo = medicalInfo,  // Enviar una cadena vacía si no hay información médica
                            fileIds = emptyList(),  // Enviar una lista vacía en lugar de null
                            parentId = currentUser?.id ?: "",  // Enviar una cadena vacía si no hay parentId
                            role = "Child",
                            registrationDate = null,
                            createdAt = null,
                            updatedAt = null,
                            active = true
                        )
                        // Llamar al callback para registrar al niño
                        onRegisterClick(child)
                    },
                    modifier = Modifier.fillMaxWidth(),
                   // enabled = !isLoading  // Deshabilitar el botón mientras se carga
                ) {
                    Text("Registrar Niño")
                }

            }
        }
    }
}

// Función para registrar al niño en el servidor
fun registerChild(child: Child, onSuccess: () -> Unit) {
    val apiService = RetrofitInstance.apiService
    val app = MyApplication()

    // Llamada a la API para registrar al niño
    apiService.registerChild(child).enqueue(object : Callback<ChildResponse> {
        override fun onResponse(call: Call<ChildResponse>, response: Response<ChildResponse>) {
            if (response.isSuccessful) {
                val registeredChild = response.body()
                if (registeredChild != null) {
                    // Actualizar la lista de childrenIds del tutor actual
                    val currentChildrenIds = app.currentUser?.childrenIds?.toMutableList() ?: mutableListOf()
                    registeredChild.id.let { currentChildrenIds.add(it) }
                    app.currentUser = app.currentUser?.copy(childrenIds = currentChildrenIds)
                    onSuccess()  // Llamar al callback de éxito
                } else {
                    Log.e(
                        "ChildRegistrationScreen",
                        "Error en el registro: ${response.message()} - ${response.errorBody()?.string()}"
                    )
                }

            }
        }
        override fun onFailure(call: Call<ChildResponse>, t: Throwable) {
            Log.e("RegisterScreen", "Error de red: ${t.message}")
        }
    })
}

@Preview(showBackground = true)
@Composable
fun ChildRegistrationScreenPreview() {
    SupercerebrosTheme {
        ChildRegistrationScreen(
            onRegisterClick = {},
            onBackClick = {}
        )
    }
}
