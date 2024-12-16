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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.biometricx.components.CButton
import com.example.biometricx.ui.theme.AlegreyaFontFamily
import com.example.biometricx.ui.theme.AlegreyaSansFontFamily


@Composable
 fun BasicInformationUser(){
    Surface (
        color = Color(0xFF253334),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxSize()

            .clip(RoundedCornerShape(10.dp)))
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
                UserBox("User Name", "19", "Female", "Father")

                //ChartsView(navController = rememberNavController())

                //CButton(text = "Registrar glucosa-represión arterial")
            }
        }
    }
 }

@Composable
fun ChartsView(
    navController: NavHostController
){
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

            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                factory = { context -> chartView(context) }
            )

            Text(
                text = "Presión",
                color = Color.Black
            )

            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Transparent),
                factory = { context -> chartBPView(context) }
            )

            Spacer(modifier = Modifier.padding(16.dp))



//            FilledTonalButton(onClick = {
//                navController.navigate("welcome")
//            }) {
//                Text ("More Data...")
//            }
//
//        }
//        Column (modifier = Modifier.padding(8.dp)) {
//            CButton(text = "Registrar Glucosa y presión arterial")
//        }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewww () {
    //ChartsView(navController = rememberNavController())
    BasicInformationUser()
}

@Composable
fun UserBox(userName: String, age: String, sex: String, relationship: String) {
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
                    text = "Data User",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = AlegreyaFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        , textAlign = TextAlign.Center
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
                        text = "Name: $userName",
                        fontFamily = AlegreyaSansFontFamily,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Age: $age",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Sex: $sex",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Relationship: $relationship",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
        }
        Column (
            modifier = Modifier.padding(top = 170.dp)
        ) {
            ChartsView(navController = rememberNavController())
        }
    }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
        ,
        Arrangement.Bottom
    ){
       CButton(text = "Registrar glucosa y presión")
        Spacer(modifier = Modifier.padding(4.dp))
       CButton(text = "bORRAR")
    }
}
