package com.supercerebros.data

import com.google.gson.annotations.SerializedName

/*
import com.google.gson.annotations.SerializedName
import com.supercerebros.models.UserOrChild

// Datos para la respuesta
data class LoginResponse(
    val success: Boolean,
    @SerializedName("user")
    val userOrChild: UserOrChild?  // Mapea "user" o "child" a UserOrChild
)*/
data class LoginResponse(
    @SerializedName("userOrChildType") val userOrChildType: String,
    @SerializedName("user") val user: Any
)
