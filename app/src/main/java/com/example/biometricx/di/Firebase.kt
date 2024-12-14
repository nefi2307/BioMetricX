package com.example.biometricx.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseObjects {
    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance()}
    val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
}