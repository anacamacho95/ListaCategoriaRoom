package com.example.listacategoriaroom.entidades

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "Tarea",
    foreignKeys = [
        ForeignKey(
            entity = Categoria::class,
            parentColumns = ["idCategoria"],
            childColumns = ["categoriaPadreId"]
        )
    ]

)
class Tarea (
    @ColumnInfo(name ="categoriaPadreId")
    var categoriaPadreId: Int,
    @ColumnInfo (name = "nombre")
    var nombre: String ): Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "idTarea")
    var idTarea = 0

    @Ignore
    lateinit var categoriaPadre: Categoria

    //una tarea tiene un conjunto de items
    @Ignore
    var items: MutableList<Item> = mutableListOf()

}