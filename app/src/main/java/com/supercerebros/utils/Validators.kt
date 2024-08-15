package com.supercerebros.utils

import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun validateName(name: String): String? {
    return when {
        name.isBlank() -> "El nombre no puede estar vacío"
        name.length < 2 -> "El nombre es demasiado corto"
        !name.all { it.isLetter() } -> "El nombre solo puede contener letras"
        else -> null
    }
}

fun validateLastName(lastName: String): String? {
    return when {
        lastName.isBlank() -> "Los apellidos no pueden estar vacíos"
        lastName.length < 2 -> "Los apellidos son demasiado cortos"
        !lastName.all { it.isLetter() || it.isWhitespace() } -> "Los apellidos solo pueden contener letras y espacios"
        else -> null
    }
}

fun validateEmail(email: String): String? {
    val emailPattern = Patterns.EMAIL_ADDRESS
    return when {
        email.isBlank() -> "El correo electrónico no puede estar vacío"
        !emailPattern.matcher(email).matches() -> "Formato de correo electrónico no válido"
        else -> null
    }
}

fun validatePhoneNumber(phoneNumber: String): String? {
    val phonePattern = Pattern.compile("^[+]?[0-9]{9,13}\$")
    return when {
        phoneNumber.isBlank() -> "El número de teléfono no puede estar vacío"
        !phonePattern.matcher(phoneNumber).matches() -> "Formato de número de teléfono no válido"
        else -> null
    }
}

fun validateDNI(dni: String): String? {
    val dniPattern = Regex("^[0-9]{8}[A-Za-z]\$")

    if (dni.isBlank()) {
        return "El DNI no puede estar vacío"
    }

    if (!dniPattern.matches(dni)) {
        return "Formato de DNI no válido"
    }

    val dniNumbers = dni.substring(0, 8).toIntOrNull()
    val dniLetter = dni.last().uppercaseChar()  // Convierte la letra a mayúscula

    if (dniNumbers != null) {
        val validLetters = "TRWAGMYFPDXBNJZSQVHLCKE"
        val correctLetter = validLetters[dniNumbers % 23]

        if (dniLetter != correctLetter) {
            return "La letra del DNI no es correcta"
        }
    } else {
        return "Los números del DNI no son válidos"
    }

    return null
}


fun validateBirthDate(birthDate: String): String? {
    return try {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = dateFormat.parse(birthDate)
        if (date.after(Date())) {
            "La fecha de nacimiento no puede ser en el futuro"
        } else {
            null
        }
    } catch (e: Exception) {
        "Formato de fecha de nacimiento no válido"
    }
}

fun validatePassword(password: String): String? {
    return when {
        password.isBlank() -> "La contraseña no puede estar vacía"
        password.length < 8 -> "La contraseña debe tener al menos 8 caracteres"
        !password.any { it.isDigit() } -> "La contraseña debe contener al menos un número"
        !password.any { it.isUpperCase() } -> "La contraseña debe contener al menos una letra mayúscula"
        !password.any { it.isLowerCase() } -> "La contraseña debe contener al menos una letra minúscula"
        !password.any { "!@#\$%^&*()_-+=<>?/{}~|".contains(it) } -> "La contraseña debe contener al menos un carácter especial"
        else -> null
    }
}

fun validateConfirmPassword(password: String, confirmPassword: String): String? {
    return when {
        confirmPassword.isBlank() -> "Debe confirmar su contraseña"
        password != confirmPassword -> "Las contraseñas no coinciden"
        else -> null
    }
}


