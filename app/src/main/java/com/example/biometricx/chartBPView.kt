package com.example.biometricx

/*class chartBPView {
}*/

import android.widget.FrameLayout
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry


class chartBPView(context: android.content.Context) : FrameLayout(context) {

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

        val dataPoints = listOf(
            Entry(0F, 2f), //Lunes
            Entry(1F, 1f), //Martes
            Entry(2F, 14f), //Miercoles
            Entry(3F, 5f), //Jueves
            Entry(4F, 4f), //Viernes
            Entry(5F, 10f), //Sábado
            Entry(6F, 13f) //Domingo
        )

        chartGenerator.generateLineChart(lineChart, dataPoints, label = "Blood Pressure")
    }
}