package com.example.biometricx

import android.content.Context
import android.widget.FrameLayout
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry


class chartView(
    context: Context,
    val glucosas: List<Float>
) :
    FrameLayout(context) {

    private var lineChart: LineChart
    private val chartGenerator = lineChart()

    init {
        lineChart = LineChart(context)
        val chartLayoutParams = LayoutParams(800, 600) // Set width and height in pixels
        chartLayoutParams.gravity = android.view.Gravity.CENTER
        addView(lineChart, chartLayoutParams)
        setupChart()
    }


    private fun setupChart() {
//        val dataPoints = listOf(
//            Entry(0F, 20f), //Lunes
//            Entry(1F, 2f), //Martes
//            Entry(2F, 4f), //Miercoles
//            Entry(3F, 8f), //Jueves
//            Entry(4F, 16f), //Viernes
//            Entry(5F, 8f), //Sábado
//            Entry(6F, 16f) //Domingo
//        )
        val dataPoints = glucosas.mapIndexed { index, glucosa ->
            Entry(index.toFloat(), glucosa) // X: índice, Y: valor de la presión
        }


        chartGenerator.generateLineChart(lineChart, dataPoints, label = "Glucose")
    }
}