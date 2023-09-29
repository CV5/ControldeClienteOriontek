package com.cv5.controldecliente.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.cv5.controldecliente.viewModel.FormularioViewModel

class MyAlertDialog() : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        val viewModel:FormularioViewModel = ViewModelProvider(requireActivity())[FormularioViewModel::class.java]

        builder.setTitle("Confirmar")
            .setMessage("quieres Borrar el cliente? tambien borraras sus Direcciones.")
            .setPositiveButton("si") { dialog, which ->
                viewModel.borrarCliente(viewModel.idCliente.value!!)
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

        return builder.create()
    }
}