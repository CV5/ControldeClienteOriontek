package com.cv5.controldecliente

import android.health.connect.datatypes.units.Length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.cv5.controldecliente.databinding.ActivityFormularioBinding
import com.cv5.controldecliente.viewModel.FormularioViewModel
import com.cv5.controldecliente.viewModel.MainViewModel

class FormularioActivity : AppCompatActivity() {
    lateinit var binding: ActivityFormularioBinding
    lateinit var viewModel: FormularioViewModel
    lateinit var arrayAdapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get()
        setContentView(binding.root)
        arrayAdapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1, viewModel.direcciones)
        binding.lvListaDirecciones.adapter = arrayAdapter
        binding.btAgregarDirecciones.setOnClickListener {
                if (binding.etDireccion.text.isNotEmpty()){
                    viewModel.direcciones.add(binding.etDireccion.text.toString())
                    binding.etDireccion.text.clear()
                    arrayAdapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(this,
                        "El campo de dirección esta vacío",
                        Toast.LENGTH_LONG).show()
                }

        }


    }
}