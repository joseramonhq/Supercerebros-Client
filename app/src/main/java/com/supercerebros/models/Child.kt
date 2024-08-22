package com.supercerebros.models

import com.google.gson.annotations.SerializedName

data class Child(
    @SerializedName("_id")
    val id: String?,  // Esto mapea el campo _id del JSON al campo id en Kotlin
    val firstName: String,
    val lastName: String,
    val birthDate: String?,
    val gender: String?,
    val medicalInfo: String?,
    val parentId: String?,    // ID del tutor asociado a este niño
    val fileIds: List<String>?,  // IDs de los archivos asociados al niño
    val registrationDate: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val active: Boolean,
    val userOrChildType: String
)

