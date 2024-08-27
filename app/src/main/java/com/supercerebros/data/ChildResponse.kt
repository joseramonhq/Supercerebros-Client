package com.supercerebros.data

data class ChildResponse(
    val id: String,          // El ID único del usuario generado por la base de datos
    val role: String,        // Rol del usuario: "Tutor" o "Child"
    val fullName: String, //Nombre del usuario
    val birthDate: String?,  // Fecha de nacimiento (puede ser nulo)
    val gender: String,
    val medicalInfo: String?,
    val email: String,
    val password: String,// Correo electrónico del usuario
    val parentId: String,   // ID del tutor (solo si el usuario es un Child)
    val fileIds: List<String>?, // Lista de IDs de archivos asociados (puede ser nulo)
    val active: Boolean
)
