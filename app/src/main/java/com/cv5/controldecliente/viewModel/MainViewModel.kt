package com.cv5.controldecliente.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cv5.controldecliente.config.ClienteApp.Companion.db
import com.cv5.controldecliente.models.Cliente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel:ViewModel() {
    //La lista de los clientes
    var clienteLista = MutableLiveData<List<Cliente>>()
    //Variable que utilizamos para el parametro de busquedas
    var parameterBusqueda = MutableLiveData<String>()


    fun iniciar(){
        viewModelScope.launch {
            clienteLista.value = withContext(Dispatchers.IO){
                db.clienteDao().obtenerTodos()
            }
        }
    }

    fun buscarPorNombre() {
        viewModelScope.launch {
            clienteLista.value = withContext(Dispatchers.IO){
                db.clienteDao().buscarPorNombre(parameterBusqueda.value!!)
            }
        }
    }


}