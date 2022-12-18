package com.hectorfortuna.tmdbapp.util

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import okhttp3.internal.notifyAll

class NetworkDialog(

private val title: String,
private val message: String,
private val textNo: String
) : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setNegativeButton(textNo) { dialogInterface, _ ->

            }
            .create()


}