package com.supercerebros.models

data class BreathingSession(
    val childId: String,
    val fillDurationMillis: Int,
    val fillPauseMillis: Int,
    val emptyDurationMillis: Int,
    val emptyPauseMillis: Int,
    val repeatCount: Int,
    val completed: Boolean
)
