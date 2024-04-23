package com.example.listacategoriaroom.interfaces

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.listacategoriaroom.entidades.Categoria
import com.example.listacategoriaroom.entidades.Item
import com.example.listacategoriaroom.entidades.Tarea

interface InterfaceDaoTareas :InterfaceDao{
    //CRUD TAREAS
    @Insert
    fun addTarea (idCategoria: Int, ta: Tarea)

    //leer Todas las tareas
    @Query("SELECT * FROM Tarea WHERE idCategoria LIKE :idCat")
    fun getTareas(idCat: Int): MutableList<Tarea>

    @Update
    fun updateNombreTarea(idCat: Int, taAnt: Tarea, taNue: Tarea)

    @Delete
    fun deleteTarea (idCat: Int, ta: Tarea)

    //CRUD ITEMS
    @Insert
    fun addItem (ca: Categoria, ta: Tarea, ite: Item)

    //leer Todos los items
    @Query("SELECT * FROM Item WHERE Tarea LIKE :ta AND Categoria LIKE :ca")
    fun getItems(ca: Categoria, ta: Tarea): MutableList<Item>

    @Update
    fun updateItem(ca: Categoria, ta: Tarea,iteAnt: Item, iteNue: Item)

    @Delete
    fun deleteItem (ca: Categoria ,ta: Tarea, ite: Item)

    //NºItems
    @Query("SELECT COUNT(*) FROM Item WHERE idTarea = :taId AND idCategoria = :caId")
    fun getNItems(ca: Categoria, ta: Tarea): Int

}