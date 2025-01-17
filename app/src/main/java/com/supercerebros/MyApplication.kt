package com.supercerebros

import android.app.Application
import com.supercerebros.models.Child
import com.supercerebros.models.User

class MyApplication : Application() {

    var currentUser: User? = null
    var currentChild: Child? = null

    // Función de login unificada que maneja tanto User como Child
    fun login(user: User) {
        this.currentUser = user
        this.currentChild = null // Asegúrate de que no haya un Child activo
    }

    fun login(child: Child) {
        this.currentUser = null // Asegúrate de que no haya un User activo
        this.currentChild = child
    }

    fun logout() {
        this.currentUser = null
        this.currentChild = null
    }

    fun isLoggedIn(): Boolean {
        return currentUser != null || currentChild != null
    }

    // Funciones auxiliares para obtener el tipo específico de usuario
    fun getUser(): User? {
        return currentUser
    }

    fun getChild(): Child? {
        return currentChild
    }

    // Función para obtener el rol del usuario actual
    fun getRole(): String? {
        return when {
            currentUser != null -> currentUser?.role
            currentChild != null -> "Child"
            else -> null
        }
    }
}
