package com.cv5.controldecliente.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cv5.controldecliente.models.Direccion


@Dao
interface DireccionesDao {
    @Query("select * from Direccion where idCliente = :id")
    suspend fun obtenerTodas(id:Long):List<Direccion>


    @Insert
    suspend fun insertarDireccion(direccion: List<Direccion>):List<Long>

    @Update
    suspend fun actualizarDireccion(direccion: Direccion)
    @Delete
    suspend fun borrarDireccion(direccion: Direccion):Int

    @Query("DELETE FROM Direccion WHERE idCliente = :clienteId")
    suspend fun borrarDireccionPorIdCliente(clienteId:Long):Int
    @Query("DELETE FROM Direccion WHERE idDireccion = :idDireccion")
    suspend fun borrarDireccionPorID(idDireccion:Long):Int
}