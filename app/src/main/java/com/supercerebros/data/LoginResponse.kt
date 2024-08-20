package com.supercerebros.data

import com.supercerebros.models.User

// Datos para la respuesta
data class LoginResponse(
    val success: Boolean,
    val user: User?  // El servidor devuelve el objeto User completo en caso de éxito
)

