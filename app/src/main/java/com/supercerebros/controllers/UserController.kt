package com.supercerebros.controllers;

import android.util.Log
import com.supercerebros.api.RetrofitInstance
import com.supercerebros.data.UserResponse
import com.supercerebros.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun registerUser(user: User, onRegisterSuccess: () -> Unit) {
    Log.i("RegisterScreen", "Datos del usuario que se enviarán: $user")

    val apiService = RetrofitInstance.apiService

    apiService.registerUser(user).enqueue(object : Callback<UserResponse> {
        override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
            if (response.isSuccessful) {
                Log.i("RegisterScreen", "Registro exitoso: ${response.body()?.id}")
                onRegisterSuccess()
            } else {
                Log.e("RegisterScreen", "Error en el registro: ${response.message()} - ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            Log.e("RegisterScreen", "Error de red: ${t.message}")
        }
    })
}

