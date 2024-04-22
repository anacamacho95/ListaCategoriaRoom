package com.example.listacategoriaroom.entidades

import java.io.Serializable

class Tarea (var nombre: String ): Serializable {

    //una tarea tiene un conjunto de items
    var items: MutableList<Item> = mutableListOf()
    var idTarea = 0
}