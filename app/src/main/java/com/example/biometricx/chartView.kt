package com.example.biometricx

import android.widget.FrameLayout
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry


class chartView(context: android.content.Context) : FrameLayout(context) {

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
            Entry(0f, 1f),
            Entry(1f, 2f),
            Entry(2f, 4f),
            Entry(3f, 8f),
            Entry(4f, 16f)
        )

        chartGenerator.generateLineChart(lineChart, dataPoints, label = "Prototype Data")
    }
}