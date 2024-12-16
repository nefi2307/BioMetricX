package com.example.biometricx.data

data class HomeUIState(
    val personas: List<Pair<String,Persona>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)