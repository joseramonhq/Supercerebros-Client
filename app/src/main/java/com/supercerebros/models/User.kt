package com.supercerebros.models


data class User(
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
    val fileIds: List<String>?
)