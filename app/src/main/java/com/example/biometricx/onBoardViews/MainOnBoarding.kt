package com.example.boardingapp.onBoardViews

import android.graphics.pdf.PdfDocument.Page
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.biometricx.R
import com.example.biometricx.data.PageData
import com.example.boardingapp.dataStore.StoreBoarding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class,
    ExperimentalFoundationApi::class)
@Composable
fun MainOnBoarding(navController: NavController, store: StoreBoarding) {
    val items = ArrayList<PageData>()

    items.add(
        PageData(
            R.raw.animation1,
            "Salud en tus Manos",
            "Monitorea y registra métricas de salud de forma sencilla para cuidar de ti y de los tuyos"
        )
    )
    items.add(
        PageData(
            R.raw.animation2,
            "Bienestar inteligente",
            "Controla su salud con gráficos claros y registros faciles, todo en un sólo lugar."
        )
    )
    items.add(
        PageData(
            R.raw.animation3,
            "Tú salud, los tuyos, su historia",
            "Empieza ya"
        )
    )

    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
        )
    
    OnBoardingPage(item = items, pagerState = pagerState, modifier = Modifier
        .fillMaxWidth()
        .background(Color.White), navController, store )

}