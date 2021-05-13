package com.heycode.runtracker.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.heycode.runtracker.repo.MainRepository

class StatisticsViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    val totalTimeInMillis = mainRepository.getTotalTimeInMillis()
    val totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()
    val totalAvgSpeed = mainRepository.getTotalAvgSpeed()
    val totalDistance = mainRepository.getTotalDistance()

    val runsSortedByDate = mainRepository.getAllRunsSortedByDate()

}