package com.heycode.runtracker.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.heycode.runtracker.R
import com.heycode.runtracker.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment : Fragment(
    R.layout.fragment_tracking
) {
    private val viewModel: MainViewModel by viewModels()
}