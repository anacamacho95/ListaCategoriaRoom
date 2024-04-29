package com.example.listacategoriaroom.entidades

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "Tarea",
    primaryKeys = ["categoriaPadreId", "categoriaHijoId"],
    foreignKeys = [
        ForeignKey(
            entity = Categoria::class,
            parentColumns = ["idCategoria"],
            childColumns = ["categoriaPadreId"]
        ),
        ForeignKey(
            entity = Categoria::class,
            parentColumns = ["idCategoria"],
            childColumns = ["categoriaHijoId"]
        )
    ],
    //añado esta linea para que room detecte que este es el unico indice para la clase item
    indices = [Index(value = ["categoriaHijoId"], unique = true)]

)
class Tarea (
    @ColumnInfo(name ="categoriaPadreId")
    var categoriaPadreId: Int,
    @ColumnInfo(name ="categoriaHijoId")
    var categoriaHijoId: Int,
    @ColumnInfo (name = "nombre")
    var nombre: String ): Serializable {

    @Ignore
    lateinit var categoriaPadre: Categoria
    @Ignore
    lateinit var categoriaHijo: Categoria

    //una tarea tiene un conjunto de items
    @Ignore
    var items: MutableList<Item> = mutableListOf()

    constructor(
        categoriaPadre: Categoria,
        categoriaHijo: Categoria,
        nombre: String = ""
    ):this (
        categoriaPadre.idCategoria,
        categoriaHijo.idCategoria,
        nombre
    ){
        this.categoriaPadre=categoriaPadre
        this.categoriaHijo=categoriaHijo
    }
}