package com.supercerebros.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
   // private const val BASE_URL = "http://10.0.2.2:3000/api/"
    private const val BASE_URL = "https://supercerebros-d184d5259aea.herokuapp.com/api/"  // URL de tu servidor Heroku

    // Configuración de Retrofit sin configuración SSL personalizada, dado que Heroku maneja SSL correctamente
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Instancia del servicio API
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
}

