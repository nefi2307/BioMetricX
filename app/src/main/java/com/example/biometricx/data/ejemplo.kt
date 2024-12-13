package com.example.biometricx.data

object Ejemplo {

    val personas = listOf(
        Persona(
            nombre = "Victor Tijerina",
            edad = 32,
            sexo = "F",
            registrosDeSalud = mutableListOf(
                RegistroSalud(
                    "2024-12-01", "8:00", 120,
                    80, 90, 36.5, "Sin síntomas adicionales."
                )
            )
        ),
        Persona(
            nombre = "Dylan Hernandez",
            edad = 19,
            sexo = "F",
            registrosDeSalud = mutableListOf(
                RegistroSalud(
                    "2024-12-01", "9:00", 130,
                    90, 90, 36.5, "Dolor agudo de cabeza."
                )
            )
        )
    )
}
