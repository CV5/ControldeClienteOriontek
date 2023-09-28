package com.cv5.controldecliente.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cv5.controldecliente.models.Cliente
@Dao
interface ClienteDao {

    //Query para obtener todos los clientes de la base de datos
    @Query("select * from Cliente")
    suspend fun obtenerTodos():List<Cliente>
    //el Insert para insertar los datos a la tabla, toma una lista por parametros
    //y retorna un long que son las llaves de cada una de las introcciones.
    @Insert
    suspend fun insertarCliente(cliente: List<Cliente>):List<Long>
    //funciòn para actualizar, recibe una instancia del objeto cliente
    // y devuelve un objeto especificando cuantas filas fueron actualizadas
    @Update suspend fun actualizarCliente(cliente:Cliente)
    //funciòn para borrar, recibe una instancia del objeto cliente
    // y devuelve un objeto especificando cuantas registro que fueron borradas
    @Delete suspend fun borrarCliente(cliente:Cliente)
}