package com.supercerebros

import android.app.Application
import com.supercerebros.models.Child
import com.supercerebros.models.User
import com.supercerebros.models.UserOrChild

class MyApplication : Application() {

    var currentUser: UserOrChild? = null

    // Función de login unificada que maneja tanto User como Child
    fun login(child: Child) {
        this.currentUser
    }
    fun login(user: User) {
        this.currentUser
    }

    fun logout() {
        this.currentUser = null
    }

    fun isLoggedIn(): Boolean {
        return currentUser != null
    }

    // Funciones auxiliares para obtener el tipo específico de usuario
    fun getUser(): User? {
        return currentUser as? User
    }

    fun getChild(): Child? {
        return currentUser as Child
    }

    // Función para obtener el rol del usuario actual
    fun getRole(): String? {
        return when (currentUser) {
            is UserOrChild.User -> "Tutor"
            is UserOrChild.Child -> "Child"
            else -> null
        }
    }
}
