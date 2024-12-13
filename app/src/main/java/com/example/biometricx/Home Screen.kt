package com.example.biometricx

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.biometricx.data.Persona
import com.example.biometricx.data.RegistroSalud

// Datos de prueba
val personas = listOf(
    Persona(
        nombre = "Victor Tijerina",
        edad = 32,
        sexo = "M",
        registrosDeSalud = mutableListOf(
            RegistroSalud("2024-12-01", "8:00", 120, 80, 90, 36.5, "Sin síntomas adicionales.")
        )
    ),
    Persona(
        nombre = "Dylan Hernandez",
        edad = 19,
        sexo = "F",
        registrosDeSalud = mutableListOf(
            RegistroSalud("2024-12-01", "9:00", 130, 90, 90, 36.5, "Dolor agudo de cabeza.")
        )
    ),
    Persona(
        nombre = "Luisa Martínez",
        edad = 30,
        sexo = "F",
        registrosDeSalud = mutableListOf(
            RegistroSalud("2024-12-02", "7:30", 115, 75, 85, 36.3, "Buen estado general.")
        )
    ),
    Persona(
        nombre = "Luisa Martínez",
        edad = 30,
        sexo = "F",
        registrosDeSalud = mutableListOf(
            RegistroSalud("2024-12-02", "7:30", 115, 75, 85, 36.3, "Buen estado general.")
        )
)
)

// Función para mostrar una lista de personas en un LazyColumn
@Composable
fun PersonasList(personas: List<Persona>, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Column para mostrar el contenido principal
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF7C9A92))
        ) {
//            Image(
//                painterResource(id = R.drawable.hi),
//                contentDescription = "Logo",
//                modifier = Modifier
//                    .padding(16.dp)
//                    .align(Alignment.CenterHorizontally)
//            )

            Box(modifier = Modifier
                .padding()
                .align(Alignment.CenterHorizontally)
            ) {
//Image(painterResource(id = R.drawable.hi) , contentDescription = "Hii")

                Text(
                    text = "Bienvenido Usuario",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, top = 15.dp),
                    color = Color.Black,
                    )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                   // .border(1.dp, Color.Black, RoundedCornerShape(12.dp)) // Aplica el borde negro
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(personas) { persona ->
                        PersonaCard(persona)
                    }
                }
            }
        }

        // FloatingActionButton
        FloatingActionButton(
            onClick = { /* Acción del FAB */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)  // Coloca el FAB en la esquina inferior derecha
                .padding(16.dp)  // Margen del FAB
//                .border(2.dp, Color.Black)
        ) {
            Icon(
                imageVector = Icons.Default.Add, // Icono de agregar
                contentDescription = "Agregar"
            )
        }
    }
}

// Composable para mostrar los detalles de una persona
@Composable
fun PersonaCard(persona: Persona) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .fillMaxWidth()
        ,
        shape = RoundedCornerShape(corner = CornerSize(26.dp)),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = " ${persona.nombre}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Edad: ${persona.edad}, Sexo: ${persona.sexo}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            /*Text(text = "Registros de Salud:", style = MaterialTheme.typography.bodyMedium)
        persona.registrosDeSalud.forEach { registro ->
            RegistroCard(registro)
        }*/
        }
    }
}

// Composable para mostrar los detalles de un registro de salud
//@Composable
//fun RegistroCard(registro: RegistroSalud) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 16.dp, top = 4.dp)
//    ) {
//        Text(
//            text = "Presión: ${registro.presionSistolica}/${registro.presionDiastolica} mmHg",
//            style = MaterialTheme.typography.bodySmall
//        )
//        Text(
//            text = "Glucosa: ${registro.glucosa} mg/dL, Temp: ${registro.temperatura}°C",
//            style = MaterialTheme.typography.bodySmall
//        )
//        if (!registro.comentarios.isNullOrEmpty()) {
//            Text(
//                text = "Comentarios: ${registro.comentarios}",
//                style = MaterialTheme.typography.bodySmall
//            )
//        }
//    }
//}

@Composable
fun FloatingButton() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Añadir")
    }
}


// Preview para mostrar cómo se vería
@Preview(showBackground = true)
@Composable
fun PreviewPersonasList() {
    MaterialTheme {
        PersonasList(personas, rememberNavController())
    }
}




