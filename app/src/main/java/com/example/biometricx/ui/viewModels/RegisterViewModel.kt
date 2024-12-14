package com.example.biometricx.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biometricx.di.FirebaseObjects
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val firebaseAuth: FirebaseAuth = FirebaseObjects.auth
    fun registerUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
        onLoading: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            onLoading(true)
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    onLoading(false)
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        onError(task.exception?.localizedMessage ?: "Registration failed")
                    }
                }
        }
    }
}