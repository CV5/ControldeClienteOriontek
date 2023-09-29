package com.cv5.controldecliente.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.cv5.controldecliente.R
import com.cv5.controldecliente.viewModel.FormularioViewModel

class MyAlertDialog() : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        val viewModel:FormularioViewModel = ViewModelProvider(requireActivity())[FormularioViewModel::class.java]

        builder.setTitle(getString(R.string.confirmar))
            .setMessage(getString(R.string.quieres_borrar_el_cliente))
            .setPositiveButton(getString(R.string.si)) { dialog, which ->
                viewModel.borrarCliente(viewModel.idCliente.value!!)
            }
            .setNegativeButton(getString(R.string.cancelar)) { dialog, which ->
                dialog.dismiss()
            }

        return builder.create()
    }
}