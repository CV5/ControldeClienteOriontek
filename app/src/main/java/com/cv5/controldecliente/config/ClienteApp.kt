package com.cv5.controldecliente.config

import android.app.Application
import androidx.room.Room

class ClienteApp: Application() {
    companion object{
        lateinit var db:ClientesDb
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            ClientesDb::class.java,
            "Cliente"
        ).build()

    }
}