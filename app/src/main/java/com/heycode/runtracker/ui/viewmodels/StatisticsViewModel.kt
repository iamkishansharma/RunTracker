package com.heycode.runtracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.heycode.runtracker.repo.MainRepository
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    val totalTimeInMillis = mainRepository.getTotalTimeInMillis()
    val totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()
    val totalAvgSpeed = mainRepository.getTotalAvgSpeed()
    val totalDistance = mainRepository.getTotalDistance()

    var runsSortedByDate = mainRepository.getAllRunsSortedByDate()

}