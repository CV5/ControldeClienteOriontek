package com.cv5.controldecliente.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyAlertDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        builder.setTitle("Confirmar")
            .setMessage("quieres Borrar el cliente? tambien borraras sus Direcciones.")
            .setPositiveButton("si") { dialog, which ->

            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

        return builder.create()
    }
}