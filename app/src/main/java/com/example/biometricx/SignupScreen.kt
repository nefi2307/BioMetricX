package com.example.biometricx

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.biometricx.components.CButton
import com.example.biometricx.components.CTextField
import com.example.biometricx.components.DontHaveAccountRow
import com.example.biometricx.ui.theme.AlegreyaFontFamily
import com.example.biometricx.ui.theme.AlegreyaSansFontFamily
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun SignupScreen(
    navController: NavHostController
) {
    Surface(
        color = Color(0xFF253334),
        modifier = Modifier.fillMaxSize()
    ) {


        Box(modifier =  Modifier.fillMaxSize()){
            /// Background Image
            Image(painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .align(Alignment.BottomCenter)
            )

            /// Content

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {

                // Logo
                Image(painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 54.dp)
                        .height(100.dp)
                        .align(Alignment.Start)
                        .offset(x = (-20).dp)
                )

                Text(text = "Sign Up",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = AlegreyaFontFamily,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )

                Text("Registrate para poder cuidar a los tuyos, llevandolo a otro nivel.",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = AlegreyaSansFontFamily,
                        color = Color(0xB2FFFFFF)
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 24.dp)
                )


                // Text Fields
                var nombre by remember { mutableStateOf("") }
                var email2 by remember { mutableStateOf("") }
                var password2 by remember { mutableStateOf("") }

                CTextField( //Nombre con verificación de solo letras y longitud mayor a 5
                    hint = "Nombre Completo",
                    value = nombre,
                    onValueChange = {
                        if (it.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"))
                            && it.length >= 5) {
                        nombre = it
                        }
                                    },
                    textStyle = TextStyle(color = Color.White)
                )

                CTextField( //Email con verificación de formato
                    hint = "Correo Electrónico",
                    value = email2,
                    onValueChange = {
                        if (android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
                            email2 = it
                        }
                        },
                    textStyle = TextStyle(color = Color.White)
                )

                CTextField( //Contraseña con verificación de longitud mayor a 8 y al menos un número
                    hint = "Contraseña",
                    value = password2,
                    onValueChange = {
                        if(it.length >= 8 && it.any { char -> char.isDigit() }
                            && it.any { char -> char.isLowerCase() }
                            && it.any { char -> char.isUpperCase() }){
                            password2 = it
                        }
                        },
                    textStyle = TextStyle(color = Color.White)
                )

                Spacer(modifier = Modifier.height(24.dp))

                CButton(text = "Ingresar")

                Row(
                    modifier = Modifier.padding(top=12.dp, bottom = 52.dp)
                ){
                    Text("Already have an account? ",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = AlegreyaSansFontFamily,
                            color = Color.White
                        )
                    )

                    Text("Sign In",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = AlegreyaSansFontFamily,
                            fontWeight = FontWeight(800),
                            color = Color.White
                        ),
                        modifier = Modifier.clickable {
                            navController.navigate("login")
                        }
                    )


                }

            }

        }

    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun SignupScreenPreview() {
    SignupScreen(rememberNavController())
}