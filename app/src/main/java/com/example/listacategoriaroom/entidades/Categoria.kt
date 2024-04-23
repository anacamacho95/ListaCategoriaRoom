package com.example.listacategoriaroom.entidades

import androidx.room.Entity
import java.io.Serializable
@Entity(tableName = "Categoria")
class Categoria ( var nombre: String): Serializable {
    //Una categoria tiene muchas tareas
    var tareas : MutableList<Tarea> = mutableListOf()
    var idCategoria = 0
}