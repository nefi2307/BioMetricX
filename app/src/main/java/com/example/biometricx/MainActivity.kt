package com.example.biometricx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.biometricx.ui.theme.MainApp
import com.example.biometricx.SignupScreen
import com.example.biometricx.WelcomeScreen
import com.example.biometricx.di.FirebaseObjects
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
    DisposableEffect(Unit) {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            isLoggedIn.value = auth.currentUser != null
        }
        firebase.addAuthStateListener(authStateListener)
        onDispose{
            firebase.removeAuthStateListener(authStateListener)
        }
    }
    NavHost(navController = navController, startDestination = "welcome") {
        // also pass navController to each screen so we can use navController in there
        composable("welcome") { WelcomeScreen(navController, isLoggedIn.value) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("home"){ PersonasList(
            personas = personas,
            navController = navController,
            onLogoutClicked = {
                firebase.signOut()
                navController.navigate("welcome"){
                    popUpTo("home") { inclusive = true }
                }
            }
        )}
        composable("AddNewUser"){ AddNewUser(navController)}
        composable("charts") { ChartsView(navController)}
    }
}