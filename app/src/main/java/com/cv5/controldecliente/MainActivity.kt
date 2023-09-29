package com.cv5.controldecliente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.cv5.controldecliente.adaptadores.ClientesAdapter
import com.cv5.controldecliente.config.Constantes
import com.cv5.controldecliente.databinding.ActivityMainBinding
import com.cv5.controldecliente.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get()
        viewModel.iniciar()
        binding.miRecycler.apply {
            layoutManager = LinearLayoutManager(applicationContext)
        }
        viewModel.clienteLista.observe(this, Observer {
            binding.miRecycler.adapter = ClientesAdapter(it)
            if (it.isEmpty()) {
                binding.tvNoCliente.visibility = View.VISIBLE
            } else {
                binding.tvNoCliente.visibility = View.GONE
            }
        })
        binding.btnFormularioActivity.setOnClickListener {
            val intent = Intent(this, FormularioActivity::class.java)
            intent.putExtra(Constantes.OPERATION_KEY, Constantes.OPERATION_INSERTAR)
            startActivity(intent)
        }
    }
}