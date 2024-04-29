package com.example.listacategoriaroom.interfaces

import androidx.room.*
import com.example.listacategoriaroom.entidades.Categoria

@Dao
interface InterfaceDaoCategorias {
    //CRUD
    @Insert
    fun addCategoria (ca: Categoria)
    @Query("SELECT * FROM Categoria")
    fun getCategorias(): MutableList<Categoria>
    @Query("SELECT * FROM Categoria WHERE nombre LIKE :nombre")
    fun getCategoria(nombre: String): Categoria?
    @Update
    fun updateCategoria(ca: Categoria)
    @Delete
    fun deleteCategoria (ca: Categoria)
}