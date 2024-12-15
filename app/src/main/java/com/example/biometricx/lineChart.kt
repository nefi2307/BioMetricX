package com.example.biometricx

import android.graphics.Color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class lineChart {
    fun generateLineChart(
        lineChart: LineChart,
        dataPoints: List<Entry>,
        xValues: List<String> = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo"),
        label: String = "Data",
        lineColor: Int = Color.BLUE,
        circleColor: Int = Color.RED
    ) {
        // Create a LineDataSet with the provided data points and label
        val lineDataSet = LineDataSet(dataPoints, label).apply {
            color = lineColor
            valueTextColor = Color.BLACK
            lineWidth = 2f
            setDrawCircles(true)
            circleRadius = 5f
            setCircleColor(circleColor)
            setDrawValues(true)
        }

        // Set the LineData to the chart
        lineChart.data = LineData(lineDataSet)

        // Configure chart appearance
        lineChart.apply {
            description.text = "Line Chart"
            setNoDataText("No data available.")
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)

            // Configure X-Axis
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                valueFormatter = IndexAxisValueFormatter(xValues)
                labelCount= 6
            }

            // Disable right Y-axis
            axisRight.isEnabled = false

            // Add animations
            animateX(1000)
        }

        // Refresh the chart
        lineChart.invalidate()
    }
}