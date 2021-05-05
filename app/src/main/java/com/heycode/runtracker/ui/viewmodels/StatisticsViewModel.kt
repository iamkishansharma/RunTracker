package com.heycode.runtracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.heycode.runtracker.repo.MainRepository
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {
    
}