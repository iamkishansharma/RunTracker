package com.heycode.runtracker.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.heycode.runtracker.R
import com.heycode.runtracker.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment(
    R.layout.fragment_statistics
) {
    private val viewModel: StatisticsViewModel by viewModels()
}