package com.cv5.controldecliente.viewModel

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

    var direccionesString: ArrayList<String> = ArrayList()
    var direcciones: ArrayList<Direccion> = ArrayList()
    var idCliente = MutableLiveData<Long>()
    var nombre = MutableLiveData<String>()
    var apellido = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var direccion = MutableLiveData<String>()
    var operacion = Constantes.OPERATION_INSERTAR
    var fueExitosaLaOperacion = MutableLiveData<Boolean>()
    var tieneDirecciones = MutableLiveData<Boolean>()
    var borroDireccion = MutableLiveData<Boolean>()

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
        viewModelScope.launch {
            var result = withContext(Dispatchers.IO) {
                db.clienteDao().insertarCliente(
                    arrayListOf(mCliente)
                )
            }
            if (result.isNotEmpty()) {
                guardarDireccionesCliente(result[0])
            }
        }
    }

    fun actualizarCliente() {
        var mCliente = Cliente(
            idCliente.value!!,
            nombre.value!!,
            apellido.value!!,
            email.value!!
        )
        viewModelScope.launch {
            var result = withContext(Dispatchers.IO) {
                db.clienteDao().actualizarCliente(
                    mCliente
                )
            }
            if (result > 0 ) {
                guardarDireccionesCliente(idCliente.value!!)
            }
        }
    }
    fun borrarDireccionPorID(dir:Direccion){
        viewModelScope.launch {
            var borrarDireccionPorID = withContext(Dispatchers.IO){
                db.direccionesDao().borrarDireccion(dir)
            }
            borroDireccion.value = borrarDireccionPorID.toString().isNotEmpty()
        }
    }
    fun borrarCliente(clienteId: Long) {
        viewModelScope.launch {
            var result = withContext(Dispatchers.IO) {
                db.clienteDao().borrarClientePorId(clienteId)
            }
            if (result.toString().isNotEmpty()) {
                var resultBorrarDirecciones = withContext(Dispatchers.IO) {
                    db.direccionesDao().borrarDireccionPorIdCliente(clienteId)
                }
                if (resultBorrarDirecciones.toString().isNotEmpty()) {
                    fueExitosaLaOperacion.value = resultBorrarDirecciones.toString().isNotEmpty()
                }
            }
        }
    }

    fun guardarDireccionesCliente(id: Long) {
//        var listaDirecion: ArrayList<Direccion> = ArrayList()
        viewModelScope.launch {
            if (!direccion.value.isNullOrEmpty()) {
                direccionesString.add(direccion.value!!)
            }
            for (myDireccion in direccionesString) {
                if (direcciones.firstOrNull { it.direccion == myDireccion } == null){
                    direcciones.add(Direccion(0, id, myDireccion))
                }
            }
            if(direcciones.filter { it.idDireccion.toInt() == 0 }.isNotEmpty()) {
                val result = withContext(Dispatchers.IO) {
                    db.direccionesDao().insertarDireccion(
                        direcciones.filter { it.idDireccion.toInt() == 0 }
                    )
                }
                fueExitosaLaOperacion.value = result.isNotEmpty()
            }else{
                fueExitosaLaOperacion.value = true

            }
        }
    }

    fun cargarDatosPorID(id: Long) {
        viewModelScope.launch {
            var clienteDb = withContext(Dispatchers.IO) {
                db.clienteDao().obtenerClientePorID(id)
            }
            var direccionesDb = withContext(Dispatchers.IO) {
                db.direccionesDao().obtenerTodas(id)
            }
            tieneDirecciones.value = direccionesDb.isNotEmpty()
            nombre.value = clienteDb.nombre
            apellido.value = clienteDb.apellido
            email.value = clienteDb.email
            direcciones.addAll(direccionesDb)
            direccionesString.addAll(direccionesDb.map { it.direccion })

        }

    }


}