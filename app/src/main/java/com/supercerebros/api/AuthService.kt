package com.supercerebros.api

import com.supercerebros.data.LoginRequest
import com.supercerebros.data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Interfaz del servicio API
interface AuthService {
    @POST("users/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}