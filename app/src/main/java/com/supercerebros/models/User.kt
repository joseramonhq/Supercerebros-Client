package com.supercerebros.models
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    val id: String?,  // Esto mapea el campo _id del JSON al campo id en Kotlin
    val role: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String?,
    val phone: String?,
    val birthDate: String?,
    val dni: String?,
    val gender: String?,
    val medicalInfo: String?,
    val parentId: String?,
    val childrenIds: List<String>?,
    val fileIds: List<String>?,
    val registrationDate: String?,
    val createdAt: String?,
    val updatedAt: String?
)

