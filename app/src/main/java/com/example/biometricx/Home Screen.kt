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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
@Composable
fun PersonasList(
    personas: List<Persona>,
    navController: NavController,
    onLogoutClicked: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Imagen de fondo que cubre toda la pantalla
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = "Fondo",
                modifier = Modifier
                    .fillMaxSize(), // Hace que la imagen ocupe todo el fondo
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = {onLogoutClicked()},
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "logout",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(4.dp)
                )
            }
            // Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Logo en la parte superior
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo), // Imagen de la persona
                        contentDescription = "Logo",
                        modifier = Modifier.size(100.dp)
                    )

                }

                // Texto de bienvenida
                Text(
                    text = "Bienvenido Usuario",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )


                // Lista de personas
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(personas) { persona ->
                        PersonaCard(persona)
                    }
                }
            }

            // FloatingActionButton 0xB2FFFFFF
            FloatingActionButton(
                onClick = { navController.navigate("AddNewUser") },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    ,
                containerColor = Color(0xB2FFFFFF)
            ) {
                Icon(
                    imageVector = Icons.Default.Add, // Icono de agregar
                    contentDescription = "Agregar"
                )
            }
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
            .background(Color.Transparent),
        shape = RoundedCornerShape(corner = CornerSize(26.dp))
    ) {
        Column(
            modifier = Modifier

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
            /*Text(text = "Registros de Salud:", style = MaterialTheme.typography.bodyMedium)
        persona.registrosDeSalud.forEach { registro ->
            RegistroCard(registro)
        }*/
        }

    }
}


// Preview para mostrar cómo se vería
@Preview(showBackground = true)
@Composable
fun PreviewPersonasList() {
    MaterialTheme {
        PersonasList(personas, rememberNavController(),{})
    }
}




