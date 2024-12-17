package com.example.biometricx.ui.components



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.biometricx.R
import com.example.biometricx.data.RegistroSalud
import com.example.biometricx.ui.theme.AlegreyaFontFamily
import com.example.biometricx.ui.viewModels.NewPersonaViewModel
import com.example.biometricx.util.CButton
import com.example.biometricx.util.CTextField

import com.google.firebase.Timestamp

@Composable
fun RegistroSaludScreen(navController: NavController, id: String) {
    val viewModel: NewPersonaViewModel = viewModel()
    val presionSistolica = remember { mutableStateOf("") }
    val presionDiastolica = remember { mutableStateOf("") }
    val glucosa = remember { mutableStateOf("") }
    val temperatura = remember { mutableStateOf("") }
    val comentarios = remember { mutableStateOf("") }

    Surface(
        color = Color(0xFF253334),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        )
        {
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .align(Alignment.BottomCenter)
            )
            Column(modifier = Modifier.padding(16.dp)) {

                Spacer(modifier = Modifier.padding(16.dp))
                Text("Registro de Datos",
                        style = TextStyle(
                        fontSize = 28.sp,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight(500),
                    color = Color.White
                )
                )
                Spacer(modifier = Modifier.padding(16.dp))

                TextField(
                    value = presionSistolica.value,
                    onValueChange = { presionSistolica.value = it },
                    label = { Text("Presión Sistólica") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = presionDiastolica.value,
                    onValueChange = { presionDiastolica.value = it },
                    label = { Text("Presión Diastólica") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = glucosa.value,
                    onValueChange = { glucosa.value = it },
                    label = { Text("Glucosa") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = temperatura.value,
                    onValueChange = { temperatura.value = it },
                    label = { Text("Temperatura") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = comentarios.value,
                    onValueChange = { comentarios.value = it },
                    label = { Text("Comentarios") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(16.dp))
                
                CButton(text = "Guardar",    onClick = {
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
                })
                
//                Button(
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                    ,
//                    onClick = {
//                    val registroSalud = RegistroSalud(
//                        fecha = Timestamp.now(),
//                        hora = Timestamp.now(),
//                        presionSistolica = presionSistolica.value.toIntOrNull() ?: 0,
//                        presionDiastolica = presionDiastolica.value.toIntOrNull() ?: 0,
//                        glucosa = glucosa.value.toIntOrNull() ?: 0,
//                        temperatura = temperatura.value.toDoubleOrNull(),
//                        comentarios = comentarios.value
//                    )
//                    viewModel.addRegistroDeSalud(
//                        personaId = id,
//                        registroSalud = registroSalud,
//                        onSuccess = {navController.navigate("home")},
//                        onError = {println(it)}
//                    )
//                }) {
//                    Text("Guardar")
//                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun prevv () {

}