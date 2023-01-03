package com.hectorfortuna.tmdbapp.util

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DetailsDialog() : DialogFragment() {

    private var yesListener: (() -> Unit)? = null
    fun setListener(listener: () -> Unit){yesListener = listener}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("SEM CONEXÃO")
            .setMessage("Volte à tela inicial para tentar novamente")
            .setCancelable(false)
            .setPositiveButton("Voltar"){ _, _ ->
                yesListener?.let {
                    it()
                }
            }
            .create()
}