package com.cv5.controldecliente.models

import androidx.room.ForeignKey
import androidx.room.PrimaryKey

data class Direccion (

    @PrimaryKey(autoGenerate = true)
    val idDireccion:Long,
    val idCliente:Long,
    var direccion:String
)
