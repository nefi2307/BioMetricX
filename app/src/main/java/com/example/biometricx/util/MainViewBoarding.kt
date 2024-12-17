package com.example.biometricx.util

import androidx.compose.runtime.Composable
import com.example.biometricx.R
import com.example.biometricx.data.PageData

@Composable
fun MainViewBoarding()
{
    val items = ArrayList<PageData>()
    items.add(
        PageData(
            R.raw.animation1,
                "Bien venido seas aqui!",
                "Aplicacion para no se que",

        )
    )

    items.add(
        PageData(
            R.raw.animation2,
            "Uso de onBoarding",
            "Está es la segunda página del Boarding mostrando una descripción",

            )
    )

    items.add(
        PageData(
            R.raw.animation3,
            "Fin del Boarding",
            "Esta es la página final del Boarding y ya debe de aparecer el botón"
        )
    )


}