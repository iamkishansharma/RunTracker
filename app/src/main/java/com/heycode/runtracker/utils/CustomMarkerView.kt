package com.heycode.runtracker.utils

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.heycode.runtracker.database.Run
import kotlinx.android.synthetic.main.marker_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class CustomMarkerView(
    val runs: List<Run>,
    context: Context,
    layoutId: Int
) : MarkerView(context, layoutId) {

    override fun getOffset(): MPPointF {
        return MPPointF(-width / 2f, -height.toFloat())
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        if (e == null) {
            return
        }
        val currentRun = e.x.toInt()
        val run = runs[currentRun]
        val calender = Calendar.getInstance().apply {
            timeInMillis = run.timestamp
        }

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = "Date: ${dateFormat.format(calender.time)}"
        tvDate.text = date

        val avgSpeed = "AvgSpeed: ${run.avgSpeedInKMH}km/hr"
        tvAvgSpeed.text = avgSpeed

        val distanceInKm = "Distance: ${run.distanceInMeters / 1000f}km"
        tvDistance.text = distanceInKm

        val dur = "Duration: ${TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)}"
        tvDuration.text = dur

        val caloriesBurned = "Calories: ${run.caloriesBurned}kcal"
        tvCaloriesBurned.text = caloriesBurned
    }
}