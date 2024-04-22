package com.example.listacategoriaroom.interfaces

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.listacategoriaroom.entidades.Categoria

interface InterfaceDaoCategorias: InterfaceDao {
    //CRUD
    //crear
    @Insert
    fun addCategoria (ca: Categoria)

    //leer Todas las categorias
    @Query("SELECT * FROM Categoria")
    fun getCategorias(): MutableList<Categoria>

    //leer una categoria en concreto
    @Query("SELECT * FROM Categoria WHERE nombre LIKE :nombre")
    fun getCategoria(nombre: String): Categoria?

    //actualizar
    @Update
    fun updateCategoria(caAnt: Categoria, caNue: Categoria)

    //borrar
    @Delete
    fun deleteCategoria (ca: Categoria)
}