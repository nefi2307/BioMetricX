package com.example.biometricx.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biometricx.data.Persona
import com.example.biometricx.di.FirebaseObjects
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState
    private val fireStore = FirebaseObjects.firestore

    init {
        fetchPersonas()
    }


    private fun fetchPersonas(){
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            fireStore.collection("personas")
                .get()
                .addOnSuccessListener { result ->
                    val personas = result.map { document ->
                        document.id to document.toObject(Persona::class.java)
                    }
                    _uiState.value = _uiState.value.copy(
                        personas = personas,
                        isLoading = false
                    )
                }
                .addOnFailureListener { error ->
                    _uiState.value = _uiState.value.copy(
                        error = error.localizedMessage
                    )
                }
        }
    }
}

data class HomeUIState(
    val personas: List<Pair<String,Persona>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

