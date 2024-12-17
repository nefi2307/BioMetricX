package com.example.biometricx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    DisposableEffect(Unit) {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            isLoggedIn.value = auth.currentUser != null
        }
        firebase.addAuthStateListener(authStateListener)
        onDispose {
            firebase.removeAuthStateListener(authStateListener)
        }
    }
    NavHost(navController = navController, startDestination = "welcome") {
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