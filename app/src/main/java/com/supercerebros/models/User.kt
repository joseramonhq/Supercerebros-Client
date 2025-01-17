package com.supercerebros.models
import com.google.gson.annotations.SerializedName



data class User(
    @SerializedName("_id")
    val id: String?,  // Esto mapea el campo _id del JSON al campo id en Kotlin
    val role: String,
    val firstName: String,
    val birthDate: String,
    val lastName: String,
    val email: String,
    val password: String?,
    val phone: String?,
    val dni: String?,
    val childrenIds: List<String>?,  // IDs de los hijos asociados a este usuario // IDs de los archivos asociados al usuario
    val registrationDate: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val active: Boolean,
    val userOrChildType: String
)
