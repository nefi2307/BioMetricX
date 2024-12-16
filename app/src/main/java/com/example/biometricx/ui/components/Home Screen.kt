package com.example.biometricx.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.biometricx.R
import com.example.biometricx.data.Persona
import com.example.biometricx.di.FirebaseObjects
import com.example.biometricx.ui.theme.AlegreyaSansFontFamily
import com.example.biometricx.ui.viewModels.HomeViewModel


@Composable
fun PersonasList(
    navController: NavController,
    onLogoutClicked: () -> Unit
) {
    val user = FirebaseObjects.auth.currentUser

    val viewModel: HomeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
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

            when {
                uiState.isLoading -> {
                    Text(
                        text = "Cargando...",
                        style = TextStyle(
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = AlegreyaSansFontFamily,
                            color = Color(0xB2FFFFFF)
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    IconButton(
                        onClick = { onLogoutClicked() },
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
                            text = "Bienvenido ${user!!.email!!}",
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
                            items(uiState.personas) { (id, persona) ->
                                PersonaCard(persona, onClick = {navController.navigate("registroSalud/$id")})
                            }
                        }
                    }
                    // FloatingActionButton 0xB2FFFFFF
                    FloatingActionButton(
                        onClick = { navController.navigate("AddNewUser") },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp),
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
    }
}

// Composable para mostrar los detalles de una persona
@Composable
fun PersonaCard(persona: Persona, onClick:() -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
        onClick = onClick,
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
        PersonasList(rememberNavController(), {})
    }
}




