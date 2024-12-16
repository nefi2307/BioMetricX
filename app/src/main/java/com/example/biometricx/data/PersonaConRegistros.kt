package com.example.biometricx.data

import com.google.firebase.Timestamp

// Clase para registrar los datos de salud de una persona
data class RegistroSalud(
    val fecha: Timestamp = Timestamp.now(),
    val hora: Timestamp = Timestamp.now(),
    val presionSistolica: Int = 0,
    val presionDiastolica: Int = 0,
    val glucosa: Int = 0,
    val temperatura: Double? = null,
    val comentarios: String? = null // Notas adicionales (opcional)
){
    constructor(): this(Timestamp.now(), Timestamp.now(), 0, 0, 0, null, null)
}

// Clase para una persona
data class Persona(
    val nombre: String,
    val edad: Int, // Edad en años
    val sexo: String, // Sexo (M/F/O)
){
    constructor() : this("", 0, "")
}
