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
    // Obtener ID de Categoria por nombre
    @Query("SELECT idCategoria FROM Categoria WHERE nombre LIKE :nombre")
    fun getCategoriaId(nombre: String): Int
    @Query("SELECT * FROM Tarea WHERE categoriaPadreId = :idCat OR categoriaHijoId = :idCat")
    fun getTareas(idCat: Int): MutableList<Tarea>
    @Update
    fun updateNombreTarea(ta: Tarea)
    @Delete
    fun deleteTarea (ta: Tarea)

    //CRUD ITEMS
    @Insert
    fun addItem (ite: Item)
    // Obtener ID de Tarea por nombre y ID de Categoria
    @Query("SELECT categoriaHijoId FROM Tarea WHERE nombre LIKE :nombre")
    fun getTareaId(nombre: String): Int
    @Query("SELECT * FROM Item WHERE tareaPadreId LIKE :id")
    fun getItems(id: Int): MutableList<Item>
    @Update
    fun updateItem(ite: Item)
    @Delete
    fun deleteItem (ite: Item)

    //NºItems
    @Query("SELECT COUNT(*) FROM Item WHERE tareaPadreId = :taId")
    fun getNItems(taId: Int): Int

}