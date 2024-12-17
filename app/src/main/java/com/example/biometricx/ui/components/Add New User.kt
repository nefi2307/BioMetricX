package com.example.biometricx.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.biometricx.util.CButton
import com.example.biometricx.util.DropDownSex
import com.example.biometricx.data.Persona
import com.example.biometricx.ui.theme.AlegreyaFontFamily
import com.example.biometricx.ui.viewModels.NewPersonaViewModel

@Composable
fun AddNewUser(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Screen(navController)
    }
}

@Composable
fun Screen(navController: NavController) {
    val addViewModel: NewPersonaViewModel = viewModel()
    var namePerson by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var sex by rememberSaveable { mutableStateOf("") }
    var parentesco by rememberSaveable { mutableStateOf("") }
    val parentescosImportantes = listOf(
        "Padre",
        "Madre",
        "Hijo",
        "Hija",
        "Hermano",
        "Hermana",
        "Abuelo",
        "Abuela"
    )

    Surface(
        color = Color(0xFF253334),
        modifier = Modifier.fillMaxSize()
    ) {

        Box(modifier = Modifier.fillMaxSize())
        {
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .align(Alignment.BottomCenter)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp, 16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Añadir Persona",
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontFamily = AlegreyaFontFamily,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )
//-----------------------Nombre ----------------------------//
                Spacer(modifier = Modifier.padding(8.dp))

                Column {

                    Text(
                        text = "    Nombre",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = AlegreyaFontFamily,
                            fontWeight = FontWeight(500),
                            color = Color.White
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = namePerson,
                        onValueChange = { namePerson = it },
                        placeholder = { Text(text = "Ej. Isaac Burciaga") },
                        shape = RoundedCornerShape(20.dp)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
//------------------ Edad ---------------------------------------- //
                    Text(
                        text = "    Edad",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = AlegreyaFontFamily,
                            fontWeight = FontWeight(500),
                            color = Color.White
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = age,
                        onValueChange = { age = it },
                        placeholder = { Text(text = "Ej. 18") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(20.dp)
                    )
// --------------------------- Sexo -------------------/
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "    Sexo",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = AlegreyaFontFamily,
                            fontWeight = FontWeight(500),
                            color = Color.White
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )
                    val sexs = listOf("Masculino", "Femenino")

                    Spacer(modifier = Modifier.padding(8.dp))
                    DropDownSex(lista = sexs, sex, onValueChange = { sex = it })
                    // ----------- Parentesco -------------/
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "    Parentesco",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = AlegreyaFontFamily,
                            fontWeight = FontWeight(500),
                            color = Color.White
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))

                    DropDownSex(
                        parentescosImportantes,
                        parentesco,
                        onValueChange = { parentesco = it })

                    Spacer(modifier = Modifier.padding(16.dp))
                    CButton(text = "Añadir persona", onClick = {
                        val persona = Persona(
                            nombre = namePerson,
                            edad = age.toInt(),
                            sexo = sex,
                            parentesco = parentesco
                        )
                        addViewModel.addPersona(
                            persona,
                            onSuccesss = {
                                navController.navigate("home")
                            },
                            onError = {
                                println(it)
                            }
                        )
                    })
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    AddNewUser(rememberNavController())
}