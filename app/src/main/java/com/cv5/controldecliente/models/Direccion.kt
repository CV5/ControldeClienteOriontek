package com.cv5.controldecliente.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity
data class Direccion (
    @PrimaryKey(autoGenerate = true)
    val idDireccion:Long,
    val idCliente:Long,
    var direccion:String
)
