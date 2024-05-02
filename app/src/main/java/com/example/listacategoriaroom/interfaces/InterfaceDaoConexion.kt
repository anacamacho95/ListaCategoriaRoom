package com.example.listacategoriaroom.interfaces

import com.example.listacategoriaroom.conexiones.BDRoom

interface InterfaceDaoConexion {
    fun createConexion (con: BDRoom)
}