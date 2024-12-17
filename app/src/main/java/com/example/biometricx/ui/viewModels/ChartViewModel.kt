package com.example.biometricx.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biometricx.data.Persona
import com.example.biometricx.di.FirebaseObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChartViewModel : ViewModel() {

    private val firestore = FirebaseObjects.firestore
    // Estado observable para la Persona
    private val _persona = MutableStateFlow<Persona?>(null)
    val persona: StateFlow<Persona?> = _persona

    // Estado observable para la lista de presiones
    private val _presionesList = MutableStateFlow<List<Float>>(emptyList())
    val presionesList: StateFlow<List<Float>> = _presionesList

    private val _glucosasList = MutableStateFlow<List<Float>>(emptyList())
    val glucosasList: StateFlow<List<Float>> = _glucosasList



    // Método para obtener la Persona
    fun cargarPersona(id: String, onError: (String) -> Unit) {
        viewModelScope.launch {
            firestore.collection("personas").document(id).get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val persona = documentSnapshot.toObject(Persona::class.java)
                        _persona.value = persona // Actualiza el StateFlow
                    } else {
                        onError("El documento no existe")
                    }
                }
                .addOnFailureListener { e ->
                    onError("Error al obtener la persona: ${e.localizedMessage}")
                }
        }
    }

    // Método para obtener la lista de presiones reales
    fun cargarPresiones(id: String, onError: (String) -> Unit) {
        viewModelScope.launch {
            firestore.collection("personas").document(id).collection("registroDeSalud").get()
                .addOnSuccessListener { registros ->
                    val listaPresiones = registros.documents.mapNotNull { registro ->
                        val sistolica = registro.getLong("presionSistolica")?.toFloat()
                        val diastolica = registro.getLong("presionDiastolica")?.toFloat()
                        if (sistolica != null && diastolica != null) {
                            (sistolica + 2 * diastolica) / 3 // Cálculo de la presión real
                        } else null
                    }
                    _presionesList.value = listaPresiones // Actualiza el StateFlow
                }
                .addOnFailureListener { e ->
                    onError("Error al obtener registros: ${e.localizedMessage}")
                }
        }
    }

    fun cargarGlucosas(id: String, onError: (String) -> Unit){
        viewModelScope.launch {
            firestore.collection("personas").document(id).collection("registroDeSalud").get()
                .addOnSuccessListener { registros ->
                    val glucosas = registros.documents.mapNotNull{ registro ->
                        registro.getLong("glucosa")?.toFloat()
                    }
                    _glucosasList.value = glucosas
                }
                .addOnFailureListener { e ->
                    onError("Error al obtener registros: ${e.localizedMessage}")
                }
        }
    }
}