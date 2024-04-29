package com.example.listacategoriaroom.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "Categoria")
class Categoria (
    @ColumnInfo(name="nombre")
    var nombre: String): Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "idCategoria")
    var idCategoria = 0

    //Una categoria tiene muchas tareas
    @Ignore
    var tareas : MutableList<Tarea> = mutableListOf()
}