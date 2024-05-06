package com.example.listacategoriaroom

import com.example.listacategoriaroom.conexiones.BDRoom
import com.example.listacategoriaroom.conexiones.BaseDatos
import com.example.listacategoriaroom.entidades.Categoria
import com.example.listacategoriaroom.entidades.Item
import com.example.listacategoriaroom.entidades.Tarea
import com.example.listacategoriaroom.interfaces.InterfaceDaoCategorias
import com.example.listacategoriaroom.interfaces.InterfaceDaoConexion
import com.example.listacategoriaroom.interfaces.InterfaceDaoTareas

class InterfaceDao: InterfaceDaoConexion, InterfaceDaoCategorias, InterfaceDaoTareas {

    lateinit var db: BDRoom

    override fun createConexion(con: BDRoom) {
        db = con as BDRoom
    }

    override fun addCategoria(ca: Categoria) {
        db.conexion.daoCategoria().addCategoria(ca)

    }

    override fun getCategorias(): MutableList<Categoria> {
        return db.conexion.daoCategoria().getCategorias()
    }

    override fun getCategoria(nombre: String): Categoria? {
        return db.conexion.daoCategoria().getCategoria(nombre)
    }

    override fun updateCategoria(ca: Categoria) {
        return db.conexion.daoCategoria().updateCategoria(ca)
    }

    override fun deleteCategoria(ca: Categoria) {
        return db.conexion.daoCategoria().deleteCategoria(ca)
    }

    override fun addTarea(ta: Tarea) {
        return db.conexion.daoTarea().addTarea(ta)

         //
    }

    override fun getCategoriaId(nombre: String): Int {
        return db.conexion.daoTarea().getCategoriaId(nombre)
    }

    override fun getTareas(idCat: Int): MutableList<Tarea> {
        return db.conexion.daoTarea().getTareas(idCat)
    }

    override fun updateNombreTarea(ta: Tarea) {
        return db.conexion.daoTarea().updateNombreTarea(ta)
    }

    override fun deleteTarea(ta: Tarea) {
        return db.conexion.daoTarea().deleteTarea(ta)
    }

    override fun addItem(ite: Item) {
        return db.conexion.daoTarea().addItem(ite)
    }

    override fun getTareaId(nombre: String): Int {
        return db.conexion.daoTarea().getTareaId(nombre)
    }

    override fun getItems(id: Int): MutableList<Item> {
        return db.conexion.daoTarea().getItems(id)
    }

    override fun updateItem(ite: Item) {
        return db.conexion.daoTarea().updateItem(ite)
    }

    override fun deleteItem(ite: Item) {
        return db.conexion.daoTarea().deleteItem(ite)
    }

    override fun getNItems(taId: Int): Int {
        return db.conexion.daoTarea().getNItems(taId)
    }

}