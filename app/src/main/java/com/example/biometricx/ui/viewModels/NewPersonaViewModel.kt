package com.example.biometricx.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.biometricx.data.Persona
import com.example.biometricx.data.RegistroSalud
import com.example.biometricx.di.FirebaseObjects
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class NewPersonaViewModel: ViewModel() {
    private val firestore: FirebaseFirestore = FirebaseObjects.firestore
    fun addPersona(persona: Persona, onSuccesss:() -> Unit, onError:(String) -> Unit){
        viewModelScope.launch {
            val personaId = firestore.collection("personas").document().id

            firestore.collection("personas").document(personaId)
                .set(persona)
                .addOnSuccessListener {
                    onSuccesss()
                }
                .addOnFailureListener{ e->
                    onError(e.localizedMessage ?: "Error al agregar la persona")
                }
        }
    }
    fun addRegistroDeSalud(
        personaId: String,
        registroSalud: RegistroSalud,
        onSuccess:() -> Unit,
        onError: (String) -> Unit
    )
    {
        viewModelScope.launch {
            val registroDeSaludRef = firestore.collection("personas").document(personaId).collection("registroDeSalud")
            registroDeSaludRef.add(registroSalud)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener {
                    onError(it.localizedMessage ?: "Error al crear el registro de la persona")
                }
        }
    }
}