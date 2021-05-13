package com.heycode.runtracker.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.heycode.runtracker.R
import com.heycode.runtracker.ui.viewmodels.StatisticsViewModel
import com.heycode.runtracker.utils.CustomMarkerView
import com.heycode.runtracker.utils.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.lang.Math.round

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        setUpBarChart()
    }

    private fun setUpBarChart() {
        barChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawLabels(false)
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(true)
        }
        barChart.axisLeft.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(true)
        }
        barChart.axisRight.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(true)
        }
        barChart.apply {
            description.text = "Avg. Speed Over Time"
            legend.isEnabled = false

        }
    }

    private fun subscribeToObservers() {
        viewModel.totalTimeInMillis.observe(
            viewLifecycleOwner,
            {
                it?.let {
                    val totalRunTime = TrackingUtility.getFormattedStopWatchTime(it)
                    tvTotalTime.text = totalRunTime
                }
            }
        )

        viewModel.totalDistance.observe(
            viewLifecycleOwner,
            {
                it?.let {
                    val totalDistance = round(it / 1000f * 10f) / 10f
                    val totalDistanceString = "${totalDistance}km"
                    tvTotalDistance.text = totalDistanceString
                }
            }
        )

        viewModel.totalAvgSpeed.observe(
            viewLifecycleOwner,
            {
                it?.let {
                    val avgSpeed = round(it * 10f) / 10f
                    val totalAvgSpeedString = "${avgSpeed}km/h"
                    tvTotalAverageSpeed.text = totalAvgSpeedString
                }
            }
        )

        viewModel.totalCaloriesBurned.observe(
            viewLifecycleOwner,
            {
                it?.let {
                    val totalCalories = "${it}kcal"
                    tvTotalCalories.text = totalCalories
                }
            }
        )

        viewModel.runsSortedByDate.observe(viewLifecycleOwner,
            {
                it?.let {
                    val allAvgSpeed =
                        it.indices.map { i -> BarEntry(i.toFloat(), it[i].avgSpeedInKMH) }
                    val barDataSet = BarDataSet(allAvgSpeed, "Avg Speed Over Time").apply {
                        valueTextColor = Color.WHITE
                        color = ContextCompat.getColor(requireContext(), R.color.colorAccent)
                    }
                    barChart.data = BarData(barDataSet)
                    barChart.marker =
                        CustomMarkerView(it.reversed(), requireContext(), R.layout.marker_view)
                    barChart.invalidate()
                }
            })

    }
}