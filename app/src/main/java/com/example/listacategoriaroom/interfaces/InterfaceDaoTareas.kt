package com.example.listacategoriaroom.interfaces

import androidx.room.*
import com.example.listacategoriaroom.entidades.Categoria
import com.example.listacategoriaroom.entidades.Item
import com.example.listacategoriaroom.entidades.Tarea

@Dao
interface InterfaceDaoTareas {

    //CRUD TAREAS
    @Insert
    fun addTarea (ta: Tarea)
    @Query("SELECT * FROM Tarea WHERE categoria = :id ")
    fun getTareas(id: Int): MutableList<Tarea>
    @Query ("SELECT * FROM Tarea WHERE nombre = :nombre")
    fun getTarea (nombre: String): Tarea
    @Update
    fun updateNombreTarea(ta: Tarea)
    @Delete
    fun deleteTarea (ta: Tarea)

    @Query("DELETE FROM Tarea") // Reemplaza 'mi_tabla' con el nombre real de tu tabla
    fun borrarTablaTarea()

    //CRUD ITEMS
    @Insert
    fun addItem (ite: Item)
    @Query("SELECT * FROM Item WHERE tarea LIKE :id")
    fun getItems(id: Int): MutableList<Item>
    @Query("SELECT * FROM Item WHERE accion = :nombre")
    fun getItem (nombre: String): Item
    @Update
    fun updateItem(ite: Item)
    @Delete
    fun deleteItem (ite: Item)

    @Query("DELETE FROM Item") // Reemplaza 'mi_tabla' con el nombre real de tu tabla
    fun borrarTablaItem()

    //NºItems
    @Query("SELECT COUNT(*) FROM Item WHERE tarea = :id")
    fun getNItems(id: Int): Int

}