package com.example.listacategoriaroom.entidades

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "Tarea",
    foreignKeys = [
        ForeignKey(
            entity = Categoria::class,
            parentColumns = ["idCategoria"], //nombre que tiene el id de la categoria Padre
            childColumns = ["categoria"], //nombre de la categoria padre en la categoria Hijo
            onDelete = ForeignKey.CASCADE // Configura la eliminación en cascada
        )
    ]

)
class Tarea (
    @ColumnInfo(name ="categoria")
    var categoria: Int,
    @ColumnInfo (name = "nombre")
    var nombre: String ): Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "idTarea")
    var idTarea = 0

    //una tarea tiene un conjunto de items
    @Ignore
    var items: MutableList<Item> = mutableListOf()

    override fun toString(): String {
        return "Tarea (nombre='$nombre', idTarea=$idTarea)"
    }


}