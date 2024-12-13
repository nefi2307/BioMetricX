package com.example.biometricx.data

// Clase para registrar los datos de salud de una persona
data class RegistroSalud(
    val fecha: String,
    val hora: String,
    val presionSistolica: Int,
    val presionDiastolica: Int,
    val glucosa: Int,
    val temperatura: Double?,
    val comentarios: String? // Notas adicionales (opcional)
)

// Clase para una persona
data class Persona(
    val nombre: String,
    val edad: Int, // Edad en años
    val sexo: String, // Sexo (M/F/O)
    val registrosDeSalud: MutableList<RegistroSalud> = mutableListOf() // Lista de registros de salud
)
