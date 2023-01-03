package com.hectorfortuna.tmdbapp.util

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CustomDialog (
    private val title: String? = null,
    private val message: String? = null,
    private val textYes: String? = null,
    private val textNo: String? = null
) : DialogFragment() {

    private var yesListener: (() -> Unit)? = null
    fun setListener(listener: () -> Unit){yesListener = listener}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(textYes){ _, _ ->
                yesListener?.let {
                    it()
                }
            }
            .setNegativeButton(textNo){ dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
}