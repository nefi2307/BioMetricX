package com.example.biometricx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.biometricx.ui.theme.MainApp
import com.example.biometricx.ui.components.SignupScreen
import com.example.biometricx.ui.components.WelcomeScreen
import com.example.biometricx.di.FirebaseObjects
import com.example.biometricx.ui.components.RegistroSaludScreen
import com.example.biometricx.ui.components.AddNewUser
import com.example.biometricx.ui.components.ChartsView
import com.example.biometricx.ui.components.LoginScreen
import com.example.biometricx.ui.components.PersonasList
import com.google.firebase.auth.FirebaseAuth
import com.example.biometricx.data.Persona
import com.example.boardingapp.dataStore.StoreBoarding
import com.example.boardingapp.onBoardViews.MainOnBoarding
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MainApp {
                NavigationView()
            }
        }
    }
}

@Composable
fun NavigationView() {

    val navController = rememberNavController()
    val firebase = FirebaseObjects.auth
    val isLoggedIn = remember { mutableStateOf(firebase.currentUser != null) }
    val context = LocalContext.current
    val dataStore = StoreBoarding(context)
    val store = dataStore.getStoreBoarding.collectAsState(initial = false)
    LaunchedEffect(Unit) {
        delay(2000)  // Espera de 2 segundos
        navController.navigate(if (store.value) "welcome" else "onBoarding") {
            popUpTo(0)  // Evitar que se vuelva atrás a la Splash Screen
        }
    }
    DisposableEffect(Unit) {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            isLoggedIn.value = auth.currentUser != null
        }
        firebase.addAuthStateListener(authStateListener)
        onDispose {
            firebase.removeAuthStateListener(authStateListener)
        }
    }
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            // Aquí es donde puedes personalizar tu Splash Screen
            SplashScreen()
        }

        composable("onBoarding"){
            MainOnBoarding(navController, dataStore)
        }
        // also pass navController to each screen so we can use navController in there
        composable("welcome") { WelcomeScreen(navController, isLoggedIn.value) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("home") {
            PersonasList2(
                navController = navController,
                onLogoutClicked = {
                    firebase.signOut()
                    navController.navigate("welcome") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
        composable("AddNewUser") { AddNewUser2(navController) }
        composable("charts") { ChartsView(navController) }
        composable(
            "registroSalud/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            BasicInformationUser(id = id, navController)
        }
        composable(
            "altaRegistroSalud/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            RegistroSaludScreen(navController, id)
        }
    }
}

@Composable
fun SplashScreen() {
    // Aquí cargamos la imagen del logo en el centro de la pantalla
    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF009688)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),  // Asegúrate de que el logo esté en res/drawable
            contentDescription = "Logo de la app",
            modifier = Modifier.size(200.dp)  // Ajusta el tamaño del logo según sea necesario
        )
    }
}
