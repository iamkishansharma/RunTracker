package com.heycode.runtracker.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.heycode.runtracker.R

class CancelDialogFrag : DialogFragment() {
    private var yesListener: (() -> Unit)? = null
    fun setYesListener(listener: () -> Unit) {
        yesListener = listener
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Cancel this Run?")
            .setMessage("Are you sure to cancel the current run and delete its all data.")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Yes") { _, _ ->
                //stopping the run
                yesListener?.let { yes ->
                    yes()
                }
            }
            .setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
    }
}