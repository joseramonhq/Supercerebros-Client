package com.supercerebros.utils

import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Valida un nombre asegurando que no esté vacío, que cada palabra tenga al menos dos caracteres y que solo contenga letras y espacios.
 *
 * @param name El nombre a validar.
 * @return Un mensaje de error si el nombre no es válido, o `null` si es válido.
 */
fun validateName(name: String): String? {
    // Divide el nombre en palabras eliminando espacios adicionales
    val words = name.trim().split("\\s+".toRegex())

    return when {
        name.isBlank() -> "El nombre no puede estar vacío"
        words.any { it.length < 2 } -> "Cada palabra del nombre debe tener al menos dos caracteres"
        !words.all { it.all { char -> char.isLetter() } } -> "El nombre solo puede contener letras y espacios"
        else -> null
    }
}

/**
 * Valida los apellidos asegurando que no estén vacíos, que cada palabra tenga al menos dos caracteres y que solo contenga letras y espacios.
 *
 * @param lastName Los apellidos a validar.
 * @return Un mensaje de error si los apellidos no son válidos, o `null` si son válidos.
 */
fun validateLastName(lastName: String): String? {
    return when {
        lastName.isBlank() -> "Los apellidos no pueden estar vacíos"
        lastName.length < 2 -> "Los apellidos son demasiado cortos"
        !lastName.all { it.isLetter() || it.isWhitespace() } -> "Los apellidos solo pueden contener letras y espacios"
        else -> null
    }
}

/**
 * Valida una dirección de correo electrónico asegurando que no esté vacía y que tenga un formato válido.
 *
 * @param email El correo electrónico a validar.
 * @return Un mensaje de error si el correo no es válido, o `null` si es válido.
 */
fun validateEmail(email: String): String? {
    val emailPattern = Patterns.EMAIL_ADDRESS
    return when {
        email.isBlank() -> "El correo electrónico no puede estar vacío"
        !emailPattern.matcher(email).matches() -> "Formato de correo electrónico no válido"
        else -> null
    }
}

/**
 * Valida un número de teléfono asegurando que no esté vacío y que cumpla con el formato internacional (entre 9 y 13 dígitos).
 *
 * @param phoneNumber El número de teléfono a validar.
 * @return Un mensaje de error si el número no es válido, o `null` si es válido.
 */
fun validatePhoneNumber(phoneNumber: String): String? {
    val phonePattern = Pattern.compile("^[+]?[0-9]{9,13}\$")
    return when {
        phoneNumber.isBlank() -> "El número de teléfono no puede estar vacío"
        !phonePattern.matcher(phoneNumber).matches() -> "Formato de número de teléfono no válido"
        else -> null
    }
}

/**
 * Valida un DNI español asegurando que tenga un formato válido (8 números seguidos de una letra) y que la letra sea correcta.
 *
 * @param dni El DNI a validar.
 * @return Un mensaje de error si el DNI no es válido, o `null` si es válido.
 */
fun validateDNI(dni: String): String? {
    val dniPattern = Regex("^[0-9]{8}[A-Za-z]\$")

    if (dni.isBlank()) {
        return "El DNI no puede estar vacío"
    }

    if (!dniPattern.matches(dni)) {
        return "Formato de DNI no válido"
    }

    // Extrae los números del DNI y la letra
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

/**
 * Valida una fecha de nacimiento asegurando que esté en formato `dd/MM/yyyy` y que no sea una fecha futura.
 *
 * @param birthDate La fecha de nacimiento a validar.
 * @return Un mensaje de error si la fecha no es válida, o `null` si es válida.
 */
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

/**
 * Valida una contraseña asegurando que no esté vacía, que tenga al menos 8 caracteres,
 * que contenga al menos un número, una letra mayúscula, una letra minúscula y un carácter especial.
 *
 * @param password La contraseña a validar.
 * @return Un mensaje de error si la contraseña no es válida, o `null` si es válida.
 */
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

/**
 * Valida la confirmación de una contraseña asegurando que no esté vacía y que coincida con la contraseña original.
 *
 * @param password La contraseña original.
 * @param confirmPassword La contraseña de confirmación.
 * @return Un mensaje de error si las contraseñas no coinciden, o `null` si coinciden.
 */
fun validateConfirmPassword(password: String, confirmPassword: String): String? {
    return when {
        confirmPassword.isBlank() -> "Debe confirmar su contraseña"
        password != confirmPassword -> "Las contraseñas no coinciden"
        else -> null
    }
}
