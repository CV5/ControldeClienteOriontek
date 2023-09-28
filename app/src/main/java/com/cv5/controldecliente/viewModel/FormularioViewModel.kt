package com.cv5.controldecliente.viewModel

import android.R
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cv5.controldecliente.config.ClienteApp.Companion.db
import com.cv5.controldecliente.config.Constantes
import com.cv5.controldecliente.models.Cliente
import com.cv5.controldecliente.models.Direccion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormularioViewModel : ViewModel() {

    var direcciones: ArrayList<String> = ArrayList()

    var id = MutableLiveData<Long>()
    var nombre = MutableLiveData<String>()
    var apellido = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var direccion = MutableLiveData<String>()
    var operacion = Constantes.OPERATION_INSERTAR
    var fueExitosaLaOperacion = MutableLiveData<Boolean>()

//    fun agregarOtraDirecion(direccion:String){
////        if (direcciones.isNotEmpty()){
//            direcciones.add(direccion)
////        }else{
////            direcciones = ArrayList()
////            direcciones.add(direccion)
////        }
//    }

    fun guardarCliente() {
        var mCliente = Cliente(
            0,
            nombre.value!!,
            apellido.value!!,
            email.value!!
        )
        when (operacion) {
            Constantes.OPERATION_INSERTAR -> {
                viewModelScope.launch {
                    var result = withContext(Dispatchers.IO) {
                        db.clienteDao().insertarCliente(
                            arrayListOf(mCliente)
                        )
                    }
                    if (result.isNotEmpty()){
                        guardarDireccionesCliente(result[0])
                    }
                }
            }

            Constantes.OPERATION_EDITAR -> {

            }
        }
    }


    fun guardarDireccionesCliente(id: Long) {
        var listaDirecion: ArrayList<Direccion> = ArrayList()
        viewModelScope.launch {
            if (direccion.value!!.isNotEmpty()) {
                direcciones.add(direccion.value!!)
            }
            for (myDireccion in direcciones){
                listaDirecion.add(Direccion(0,id,myDireccion))
            }
            var result = withContext(Dispatchers.IO) {
                db.direccionesDao().insertarDireccion(
                    listaDirecion
                )
            }

                fueExitosaLaOperacion.value = result.isNotEmpty()

        }

    }
}