package com.example.listacategoriaroom.interfaces

import com.example.listacategoriaroom.conexiones.BDRoom

interface InterfaceDao {
    fun createConexion (con: BDRoom)
}