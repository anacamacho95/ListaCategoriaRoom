package com.example.listacategoriaroom.entidades

import androidx.room.*
import java.io.Serializable

@Entity (tableName = "Item",
    foreignKeys = [
        ForeignKey(
            entity = Tarea::class,
            parentColumns = ["idTarea"],
            childColumns = ["tarea"]
        )
    ]
)
class Item (
    @ColumnInfo(name ="tarea")
    var tarea: Int,
    @ColumnInfo (name = "accion")
    var accion: String = "",
    @ColumnInfo (name = "activo" )
    var activo: Boolean = false): Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "idItem")
    var idItem = 0

    override fun toString(): String {
        return "Item( id=$idItem, accion='$accion', activo=$activo)"
    }


}