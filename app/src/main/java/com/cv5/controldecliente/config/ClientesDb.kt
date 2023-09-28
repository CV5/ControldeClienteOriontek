package com.cv5.controldecliente.config

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.cv5.controldecliente.dao.ClienteDao
import com.cv5.controldecliente.dao.DireccionesDao
import com.cv5.controldecliente.models.Cliente
import com.cv5.controldecliente.models.Direccion

@Database(
    entities = [Cliente::class, Direccion::class],
    version = 1

)
abstract class ClientesDb: RoomDatabase() {
    abstract fun  clienteDao():ClienteDao
    abstract fun  direccionesDao():DireccionesDao
}