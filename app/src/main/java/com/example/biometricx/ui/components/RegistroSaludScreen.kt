package com.example.biometricx.ui.components



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.biometricx.data.RegistroSalud
import com.example.biometricx.ui.viewModels.NewPersonaViewModel

import com.google.firebase.Timestamp

@Composable
fun RegistroSaludScreen(navController: NavController, id: String) {
    val viewModel: NewPersonaViewModel = viewModel()
    val presionSistolica = remember { mutableStateOf("") }
    val presionDiastolica = remember { mutableStateOf("") }
    val glucosa = remember { mutableStateOf("") }
    val temperatura = remember { mutableStateOf("") }
    val comentarios = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = presionSistolica.value,
            onValueChange = { presionSistolica.value = it },
            label = { Text("Presión Sistólica") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = presionDiastolica.value,
            onValueChange = { presionDiastolica.value = it },
            label = { Text("Presión Diastólica") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = glucosa.value,
            onValueChange = { glucosa.value = it },
            label = { Text("Glucosa") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = temperatura.value,
            onValueChange = { temperatura.value = it },
            label = { Text("Temperatura") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = comentarios.value,
            onValueChange = { comentarios.value = it },
            label = { Text("Comentarios") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = {
            val registroSalud = RegistroSalud(
                fecha = Timestamp.now(),
                hora = Timestamp.now(),
                presionSistolica = presionSistolica.value.toIntOrNull() ?: 0,
                presionDiastolica = presionDiastolica.value.toIntOrNull() ?: 0,
                glucosa = glucosa.value.toIntOrNull() ?: 0,
                temperatura = temperatura.value.toDoubleOrNull(),
                comentarios = comentarios.value
            )
            viewModel.addRegistroDeSalud(
                personaId = id,
                registroSalud = registroSalud,
                onSuccess = {navController.navigate("home")},
                onError = {println(it)}
            )
        }) {
            Text("Guardar")
        }
    }
}