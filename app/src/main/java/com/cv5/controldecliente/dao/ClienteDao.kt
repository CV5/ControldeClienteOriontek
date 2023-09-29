package com.cv5.controldecliente.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cv5.controldecliente.models.Cliente
@Dao
interface ClienteDao {

    @Query("select * from Cliente")
    suspend fun obtenerTodos():List<Cliente>

    @Query("select * from Cliente where idCliente =:id")
    suspend fun obtenerClientePorID(id:Long):Cliente

    @Query("select * from Cliente where nombre LIKE '%' || :name || '%' OR apellido LIKE '%' || :name || '%'")
    suspend fun buscarPorNombre(name:String):List<Cliente>

    @Insert
    suspend fun insertarCliente(cliente: List<Cliente>):List<Long>

    @Update suspend fun actualizarCliente(cliente:Cliente): Int

    @Delete suspend fun borrarCliente(cliente:Cliente): Int


    @Query("DELETE FROM cliente WHERE idCliente = :clienteId")
    suspend fun borrarClientePorId(clienteId:Long): Int
}