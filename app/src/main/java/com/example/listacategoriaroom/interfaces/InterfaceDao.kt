package com.example.listacategoriaroom.interfaces

import com.example.listacategoriaroom.conexiones.BDRoom
import com.example.listacategoriaroom.entidades.Categoria

class InterfaceDao: InterfaceDaoConexion, InterfaceDaoCategorias {
    override fun addCategoria(ca: Categoria) {
        TODO("Not yet implemented")
    }

    override fun getCategorias(): MutableList<Categoria> {
        TODO("Not yet implemented")
    }

    override fun getCategoria(nombre: String): Categoria? {
        TODO("Not yet implemented")
    }

    override fun updateCategoria(ca: Categoria) {
        TODO("Not yet implemented")
    }

    override fun deleteCategoria(ca: Categoria) {
        TODO("Not yet implemented")
    }

    override fun createConexion(con: BDRoom) {
        TODO("Not yet implemented")
    }

}