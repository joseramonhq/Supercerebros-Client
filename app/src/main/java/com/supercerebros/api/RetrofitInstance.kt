package com.supercerebros.api

import android.content.Context
import com.supercerebros.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object RetrofitInstance {

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

