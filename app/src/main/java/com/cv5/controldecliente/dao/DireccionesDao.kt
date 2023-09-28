package com.cv5.controldecliente.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cv5.controldecliente.models.Direccion


@Dao
interface DireccionesDao {
    @Query("select * from Direccion")
    suspend fun obtenerTodas():List<Direccion>

    @Insert
    suspend fun insertarDireccion(direccion: List<Direccion>):List<Long>

    @Update
    suspend fun actualizarDireccion(direccion: Direccion)

    @Delete
    suspend fun borrarDireccion(direccion: Direccion)
}