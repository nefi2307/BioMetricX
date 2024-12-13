package com.example.biometricx

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biometricx.components.CButton
import com.example.biometricx.components.CTextField
import com.example.biometricx.ui.theme.AlegreyaFontFamily

@Composable
fun AddNewUser (){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Screen()
    }
}

@Composable
fun Screen() {
    var namePerson by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
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

                Text(
                    text = "Nombre",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = AlegreyaFontFamily,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = namePerson,
                    onValueChange = { namePerson = it },
                    placeholder = { Text(text = "Ej. Isaac Burciaga") }
                )
                Spacer(modifier = Modifier.padding(8.dp))
//------------------ Edad ---------------------------------------- //
                Text(
                    text = "Edad",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = AlegreyaFontFamily,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = age,
                    onValueChange = { age = it },
                    placeholder = { Text(text = "Ej. 18") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
// --------------------------- Sexo -------------------/
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Sexo",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = AlegreyaFontFamily,
                            fontWeight = FontWeight(500),
                            color = Color.White
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )
                    val sexs = listOf("Masculino", "Femenino")
                    DropDownSex(lista = sexs)

                    // ----------- Parentesco -------------/
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Parentesco",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = AlegreyaFontFamily,
                            fontWeight = FontWeight(500),
                            color = Color.White
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))

                    DropDownSex(parentescosImportantes)

                    Spacer(modifier = Modifier.padding(12.dp))
                    CButton(text = "Añadir persona")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
 fun preview() {
    AddNewUser()
}