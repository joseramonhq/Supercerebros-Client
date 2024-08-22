package com.supercerebros.models

// Clase sellada para contener User o Child
sealed class UserOrChild {
    data class User(
        val id: String?,
        val role: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String?,
        val phone: String?,
        val birthDate: String,
        val dni: String?,
        val childrenIds: List<String>?,
        val createdAt: String?,
        val updatedAt: String?,
        val active: Boolean
    ) : UserOrChild()

    data class Child(
        val id: String?,
        val firstName: String,
        val lastName: String,
        val birthDate: String?,
        val gender: String?,
        val medicalInfo: String?,
        val parentId: String?,
        val email: String,
        val password: String?,
        val filesIds: List<String>?,
        val createdAt: String?,
        val updatedAt: String?,
        val active: Boolean
    ) : UserOrChild()
}


// Datos para la respuesta
