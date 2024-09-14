package com.supercerebros.data

data class BreathResponse(
    val id: String,
    val childId: String,
    val inhaleDuration: Int,
    val holdBreathDuration: Int,   // Duración de la retención de la respiración en segundos
    val exhaleDuration: Int,       // Duración de la exhalación en segundos
    val pauseDuration: Int,        // Duración de la pausa entre ciclos de respiración en segundos
    val totalDuration: Int,        // Duración total de la sesión en segundos
    val completed: Boolean         // Indicador de si la sesión fue completada
)
