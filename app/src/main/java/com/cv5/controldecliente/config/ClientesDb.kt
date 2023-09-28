package com.cv5.controldecliente.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cv5.controldecliente.dao.ClienteDao
import com.cv5.controldecliente.models.Cliente

@Database(
    entities = [Cliente::class],
    version = 1
)
abstract class ClientesDb: RoomDatabase() {
    abstract fun  clienteDao():ClienteDao
}