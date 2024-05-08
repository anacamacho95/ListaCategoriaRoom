package com.example.listacategoriaroom.interfaces

import androidx.room.*
import com.example.listacategoriaroom.entidades.Categoria
import com.example.listacategoriaroom.entidades.Item
import com.example.listacategoriaroom.entidades.Tarea

@Dao
interface InterfaceDaoTareas {
    //getId
    @Query("SELECT idTarea FROM Tarea WHERE nombre LIKE :nombre")
    fun getTareaId(nombre: String): Int
    @Query("SELECT idItem FROM Item WHERE accion LIKE :nombre")
    fun getItemId(nombre: String): Int

    //CRUD TAREAS
    @Insert
    fun addTarea (ca: Categoria, ta: Tarea)
    // Obtener ID de Categoria por nombre
    @Query("SELECT * FROM Tarea WHERE categoriaPadreId = :idCategoria ")
    fun getTareas(ca: Categoria): MutableList<Tarea>
    @Query ("SELECT * FROM Tarea WHERE idTarea = :id")
    fun getTarea (ta: Tarea): Tarea?
    @Update
    fun updateNombreTarea(ca: Categoria, taAnt: Tarea, taNue: Tarea)
    @Delete
    fun deleteTarea (ca: Categoria, ta: Tarea)

    //CRUD ITEMS
    @Insert
    fun addItem (ite: Item)
    @Query("SELECT * FROM Item WHERE tareaPadreId LIKE :id")
    fun getItems(id: Int): MutableList<Item>
    @Query("SELECT * FROM Item WHERE idItem = :id")
    fun getItem (it: Item): Item?
    @Update
    fun updateItem(ite: Item)
    @Delete
    fun deleteItem (ite: Item)

    //NºItems
    @Query("SELECT COUNT(*) FROM Item WHERE tareaPadreId = :taId")
    fun getNItems(taId: Int): Int

}