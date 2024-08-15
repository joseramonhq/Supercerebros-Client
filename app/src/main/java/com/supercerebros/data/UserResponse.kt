package com.supercerebros.data

data class UserResponse(
    val id: String,          // El ID único del usuario generado por la base de datos
    val role: String,        // Rol del usuario: "Tutor" o "Child"
    val firstName: String,   // Nombre del usuario
    val lastName: String,    // Apellidos del usuario
    val email: String,       // Correo electrónico del usuario
    val phone: String?,      // Número de teléfono (puede ser nulo)
    val birthDate: String?,  // Fecha de nacimiento (puede ser nulo)
    val dni: String?,        // DNI del usuario (puede ser nulo)
    val childrenIds: List<String>?,  // Lista de IDs de los hijos (solo si el usuario es un Tutor)
    val parentId: String?,   // ID del tutor (solo si el usuario es un Child)
    val fileIds: List<String>? // Lista de IDs de archivos asociados (puede ser nulo)
)