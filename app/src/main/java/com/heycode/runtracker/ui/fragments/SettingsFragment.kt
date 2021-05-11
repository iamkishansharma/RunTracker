package com.heycode.runtracker.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.heycode.runtracker.R
import com.heycode.runtracker.utils.Constants.KEY_NAME
import com.heycode.runtracker.utils.Constants.KEY_WEIGHT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(
    R.layout.fragment_settings
) {
    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDetailsFromSharedPref()

        btnApplyChanges.setOnClickListener {
            if (applyChangesToSharedPref()) {
                Toast.makeText(
                    requireContext(),
                    "Details updated!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Snackbar.make(
                    requireView(),
                    "😥 Oops! something went wrong.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun getDetailsFromSharedPref() {
        val name = sharedPref.getString(KEY_NAME, "") ?: ""
        val weight = sharedPref.getFloat(KEY_WEIGHT, 0f)

        etName.setText(name)
        etWeight.setText(weight.toString())

    }

    private fun applyChangesToSharedPref(): Boolean {
        val name = etName.text.toString()
        val weight = etWeight.text.toString()
        if (name.isEmpty()) {
            etName.error = "Required field!"
            return false
        }
        if (weight.isEmpty()) {
            etWeight.error = "Required field!"
            return false
        }

        sharedPref.edit()
            .putString(KEY_NAME, name)
            .putFloat(KEY_WEIGHT, weight.toFloat())
            .apply()

        val toolbarTitle = "Let's go $name!"
        requireActivity().tvToolbarTitle.text = toolbarTitle
        return true

    }
}