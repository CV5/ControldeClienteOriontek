package com.cv5.controldecliente.viewModel

import android.R
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cv5.controldecliente.config.Constantes

class FormularioViewModel: ViewModel() {

    lateinit var direcciones: ArrayList<String>

    var id = MutableLiveData<Long>()
    var nombre = MutableLiveData<String>()
    var apellido = MutableLiveData<String>()
    var email = MutableLiveData<String>()
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

    init {
        direcciones = ArrayList()
    }

    fun guardarCliente(){
       when(operacion){
           Constantes.OPERATION_INSERTAR->{
           }

           Constantes.OPERATION_EDITAR-> {

           }
        }
    }

    fun guardarDirección(){

    }
}