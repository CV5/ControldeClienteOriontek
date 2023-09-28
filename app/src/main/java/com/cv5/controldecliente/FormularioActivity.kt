package com.cv5.controldecliente

import android.content.Intent
import android.health.connect.datatypes.units.Length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.cv5.controldecliente.config.Constantes
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
        binding.modelo = viewModel
        binding.lifecycleOwner = this

        viewModel.operacion = intent.getStringExtra(Constantes.OPERATION_KEY)!!
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            viewModel.direcciones
        )
        binding.lvListaDirecciones.adapter = arrayAdapter

        binding.btAgregarDirecciones.setOnClickListener {
                if (viewModel.direccion.value!!.isNotEmpty()){
                    viewModel.direcciones.add(viewModel.direccion.value!!)
                    binding.etDireccion.text.clear()
                    arrayAdapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(this,
                        "El campo de dirección esta vacío",
                        Toast.LENGTH_LONG).show()
                }

        }


        viewModel.fueExitosaLaOperacion.observe(this, Observer {

            if (it){
                mostrarMensaje("Operación Exitosa");
                irAlInicio();
            }else{
                mostrarMensaje("Operación Fallida");

            }

        })


    }

    private fun irAlInicio() {
        val intent = Intent(applicationContext,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun mostrarMensaje(s: String) {
        Toast.makeText(applicationContext,s,Toast.LENGTH_LONG).show()
    }
}