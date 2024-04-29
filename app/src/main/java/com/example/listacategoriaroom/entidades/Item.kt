package com.example.listacategoriaroom.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import java.io.Serializable

@Entity (tableName = "Item",
    primaryKeys = [ "tareaPadreId", "tareaHijoId"],
    foreignKeys = [
        ForeignKey(
            entity = Tarea::class,
            parentColumns = ["categoriaHijoId"],
            childColumns = ["tareaPadreId"]
        ),
        ForeignKey(
            entity = Tarea::class,
            parentColumns = ["categoriaHijoId"],
            childColumns = ["tareaHijoId"]
        ),
    ]
)
class Item (
    @ColumnInfo(name ="tareaPadreId")
    var tareaPadreId: Int,
    @ColumnInfo(name ="tareaHijoId")
    var tareaHijoId: Int,
    @ColumnInfo (name = "accion")
    var accion: String = "",
    @ColumnInfo (name = "activo" )
    var activo: Boolean = false): Serializable {

    @Ignore
    lateinit var tareaPadre: Tarea
    @Ignore
    lateinit var tareaHijo: Tarea

    constructor(
        tareaPadre: Tarea,
        tareaHijo: Tarea,
        accion: String = "",
        activo: Boolean = false
    ): this (
        tareaPadre.categoriaHijoId,
        tareaHijo.categoriaHijoId,
        accion,
        activo
    ){
        this.tareaPadre = tareaPadre
        this.tareaHijo = tareaHijo
    }
}