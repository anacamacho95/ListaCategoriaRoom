package com.example.listacategoriaroom.interfaces

import androidx.room.*
import com.example.listacategoriaroom.entidades.Categoria

@Dao
interface InterfaceDaoCategorias {
    //get Id
    @Query("SELECT idCategoria FROM Categoria WHERE nombre LIKE :nombre")
    fun getCategoriaId(nombre: String): Int

    //CRUD
    @Insert
    fun addCategoria (ca: Categoria)
    @Query("SELECT * FROM Categoria")
    fun getCategorias(): MutableList<Categoria>
    @Query("SELECT * FROM Categoria WHERE idCategoria = :idCategoria")
    fun getCategoria(ca: Categoria): Categoria?
    @Update
    fun updateCategoria(ca: Categoria)
    @Delete
    fun deleteCategoria (ca: Categoria)
}