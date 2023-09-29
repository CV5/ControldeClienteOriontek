package com.cv5.controldecliente.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.cv5.controldecliente.config.Constantes
import com.cv5.controldecliente.databinding.ActivityFormularioBinding
import com.cv5.controldecliente.dialog.MyAlertDialog
import com.cv5.controldecliente.viewModel.FormularioViewModel

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
            viewModel.direccionesString
        )
        binding.lvListaDirecciones.adapter = arrayAdapter
        binding.lvListaDirecciones.setOnItemClickListener { parent, view, position, id ->
            val element = arrayAdapter.getItem(position)
            when(viewModel.operacion){
                Constantes.OPERATION_INSERTAR->{
                    arrayAdapter.remove(element)
                    mostrarMensaje("Dirección eliminada")
                }
                Constantes.OPERATION_EDITAR->{
                    val inDatabase = viewModel.direcciones.firstOrNull { it.direccion == element }
                    if (inDatabase != null){
                        viewModel.borrarDireccionPorID(viewModel.direcciones.first { it.direccion == element })
                        arrayAdapter.remove(element)
                        mostrarMensaje("Dirección eliminada")
                    }else{
                        arrayAdapter.remove(element)
                        mostrarMensaje("Dirección eliminada")
                    }
                }
            }
        }
        binding.btAgregarDirecciones.setOnClickListener {
                if (!viewModel.direccion.value.isNullOrEmpty()){
                    viewModel.direccionesString.add(viewModel.direccion.value!!)
                    binding.etDireccion.text.clear()
                    arrayAdapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(this,
                        "El campo de dirección esta vacío",
                        Toast.LENGTH_LONG).show()
                }
        }
        if (viewModel.operacion == Constantes.OPERATION_EDITAR){
            viewModel.idCliente.value = intent.getLongExtra(Constantes.ID_CLIENTE_KEY,0)
            binding.btnBorrarCliente.visibility = View.VISIBLE
            binding.btnEditarCliente.visibility = View.VISIBLE
            viewModel.cargarDatosPorID(viewModel.idCliente.value!!)
            binding.btGuardarCliente.visibility = View.GONE
        }else{
            binding.btnBorrarCliente.visibility = View.GONE
            binding.btnEditarCliente.visibility = View.GONE
            binding.btGuardarCliente.visibility = View.VISIBLE
        }
        viewModel.fueExitosaLaOperacion.observe(this, Observer {
            if (it){
                mostrarMensaje("Operación Exitosa");
                irAlInicio();
            }else{
                mostrarMensaje("Operación Fallida");
            }
        })
        viewModel.tieneDirecciones.observe(this, Observer {
            if (it){
                arrayAdapter.notifyDataSetChanged()
            }
        })
        viewModel.borroDireccion.observe(this, Observer {
            mostrarMensaje("Se borró con exito la dirección")
            arrayAdapter.notifyDataSetChanged()
        })

        binding.btnBorrarCliente.setOnClickListener {
            val dialogFragment = MyAlertDialog()
            dialogFragment.show(supportFragmentManager, "Confirmation")
        }
    }
    private fun irAlInicio() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    private fun mostrarMensaje(s: String) {
        Toast.makeText(applicationContext,s,Toast.LENGTH_LONG).show()
    }
}