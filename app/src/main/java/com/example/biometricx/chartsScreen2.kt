package com.example.biometricx

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.biometricx.data.Persona
import com.example.biometricx.util.CButton
import com.example.biometricx.ui.theme.AlegreyaFontFamily
import com.example.biometricx.ui.theme.AlegreyaSansFontFamily
import com.example.biometricx.ui.viewModels.ChartViewModel
import com.github.mikephil.charting.data.Entry


@Composable
fun BasicInformationUser(id: String, navController: NavController) {
    Surface(
        color = Color(0xFF253334),
        modifier = Modifier.fillMaxSize()
    ) {
        val viewModel: ChartViewModel = viewModel()
        val persona by viewModel.persona.collectAsState()
        val presionesList by viewModel.presionesList.collectAsState()
        val glucosasList by viewModel.glucosasList.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.cargarPersona(id) { error -> println(error) }
            viewModel.cargarPresiones(id) { error -> println(error) }
            viewModel.cargarGlucosas(id){ error -> println(error)}
        }
        for(element in presionesList){
            println("el valor es $element")
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
        )
        {
            Image(
                painterResource(id = R.drawable.bg),
                modifier = Modifier.fillMaxSize(),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp), // Espaciado entre recuadros
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // user box para mostrar los datos, función se muestra hasta abajo
                UserBox(
                    persona ?: Persona(),
                    onButtonAltaRegistroPressed = {
                        navController.navigate("altaRegistroSalud/$id")
                    },
                    presiones = presionesList,
                    glucosasList = glucosasList
                )
            }
        }
    }
}

@Composable
fun ChartsView(presiones:List<Float>, glucosasList: List<Float>) {
    Box(//terminal
        modifier = Modifier
            .background(color = Color.LightGray)
    ) {
        //Image(painterResource(id = R.drawable.bg1), contentDescription = "")
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Glucosa",
                color = Color.Black
            )
            if (glucosasList.isEmpty()) {
                Text(
                    text = "Cargando datos...",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }else{
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    factory = { context -> chartView(context, glucosas = glucosasList) }
                )
            }
            Text(
                text = "Presión",
                color = Color.Black
            )
            if (presiones.isEmpty()) {
                Text(
                    text = "Cargando datos...",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }else{
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color.Transparent),
                    factory = { context -> chartBPView(context,presiones) }
                )
            }

            Spacer(modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun UserBox(
    persona: Persona,
    onButtonAltaRegistroPressed: () -> Unit,
    presiones: List<Float>,
    glucosasList: List<Float>
) {
    Box(
        modifier = Modifier
            .height(720.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.LightGray)
            .height(200.dp) // Aumenté la altura para acomodar más elementos
            .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp) // Espaciado entre los elementos
        ) {
            item {
                // Título
                Text(
                    text = "Registro de tu familiar",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = AlegreyaFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .padding(8.dp), textAlign = TextAlign.Center
                )
            }
            item {
                // Información del usuario
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Nombre: ${persona.nombre}",
                        fontFamily = AlegreyaSansFontFamily,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Edad: ${persona.edad}",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Sexo: ${persona.sexo}",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Relacion: ${persona.parentesco}",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }
        Column(
            modifier = Modifier.padding(top = 170.dp)
        ) {
            ChartsView(presiones = presiones, glucosasList = glucosasList)
        }
    }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        Arrangement.Bottom
    ) {
        CButton(text = "Registrar glucosa y presión", onClick = {onButtonAltaRegistroPressed()})
        Spacer(modifier = Modifier.padding(4.dp))
        CButton(text = "Borrar")
    }
}
