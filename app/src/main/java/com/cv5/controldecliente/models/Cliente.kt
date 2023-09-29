package com.cv5.controldecliente.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Cliente (
    @PrimaryKey(autoGenerate = true)
    val idCliente:Long,
    var nombre:String,
    var apellido:String,
    var email:String
)
