package com.example.listacategoriaroom.entidades

import java.io.Serializable

class Categoria ( var nombre: String): Serializable {
    //Una categoria tiene muchas tareas
    var tareas : MutableList<Tarea> = mutableListOf()
    var idCategoria = 0
}