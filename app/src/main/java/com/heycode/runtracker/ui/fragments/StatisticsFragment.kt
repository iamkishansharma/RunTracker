package com.heycode.runtracker.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.heycode.runtracker.R
import com.heycode.runtracker.ui.viewmodels.StatisticsViewModel
import com.heycode.runtracker.utils.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.android.synthetic.main.item_run.*
import java.lang.Math.round

@AndroidEntryPoint
class StatisticsFragment : Fragment(
    R.layout.fragment_statistics
) {
    private val viewModel: StatisticsViewModel by viewModels()
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
                    tvDistance.text = totalDistanceString
                }
            }
        )

        viewModel.totalAvgSpeed.observe(
            viewLifecycleOwner,
            {
                it?.let {
                    val avgSpeed = round(it * 10f) / 10f
                    val totalAvgSpeedString = "${avgSpeed}km/h"
                    tvAverageSpeed.text = totalAvgSpeedString
                }
            }
        )

        viewModel.totalCaloriesBurned.observe(
            viewLifecycleOwner,
            {
                it?.let {
                    val totalCalories = "${it}kcal"
                    tvAverageSpeed.text = totalCalories
                }
            }
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }
}