import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.example.biometricx.R
import com.example.biometricx.components.CButton
import com.example.biometricx.components.DropDownSex
import com.example.biometricx.ui.theme.AlegreyaFontFamily

@Composable
fun AddNewUser() {
    var showAnimation by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (showAnimation) {
            AnimationScreen { showAnimation = false }
        } else {
            Screen(onAddPersonClick = { showAnimation = true })
        }
    }
}

@Composable
fun Screen(onAddPersonClick: () -> Unit) {
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
    val sexs = listOf("Masculino", "Femenino")

    Surface(
        color = Color(0xFF253334),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
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

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Nombre",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight(500),
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(6.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = namePerson,
                onValueChange = { namePerson = it },
                placeholder = { Text(text = "Ej. Isaac Burciaga") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Edad",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight(500),
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(6.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = age,
                onValueChange = { age = it },
                placeholder = { Text(text = "Ej. 18") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Sexo",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight(500),
                    color = Color.White
                )
            )
            DropDownSex(lista = sexs)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Parentesco",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight(500),
                    color = Color.White
                )
            )
            DropDownSex(lista = parentescosImportantes)

            Spacer(modifier = Modifier.height(16.dp))

            CButton(text = "Añadir persona", onClick = { onAddPersonClick() })
        }
    }
}

@Composable
fun AnimationScreen(onAnimationEnd: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.success))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1
    )

    // Detecta cuando la animación llega al final
    LaunchedEffect(progress) {
        if (progress == 1f) { // 1f significa que la animación ha terminado
            onAnimationEnd()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(200.dp)
        )
    }
}