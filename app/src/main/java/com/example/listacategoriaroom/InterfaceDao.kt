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
    //---------- NUEVO
    override fun getCategoriaId(nombre: String): Int {
        return db.conexion.daoCategoria().getCategoriaId(nombre)
    }

    override fun getTareaId(nombre: String): Int {
        return db.conexion.daoTarea().getTareaId(nombre)
    }

    override fun getItemId(nombre: String): Int {
        return  db.conexion.daoTarea().getItemId(nombre)
    }
    // ----------------
    override fun addCategoria(ca: Categoria) {
        db.conexion.daoCategoria().addCategoria(ca)
    }

    override fun getCategorias(): MutableList<Categoria> {
        return db.conexion.daoCategoria().getCategorias()
    }

    override fun getCategoria(ca: Categoria): Categoria? {
        return db.conexion.daoCategoria().getCategoria(ca)
    }

    override fun updateCategoria(ca: Categoria) {
        return db.conexion.daoCategoria().updateCategoria(ca)
    }

    override fun deleteCategoria(ca: Categoria) {
        return db.conexion.daoCategoria().deleteCategoria(ca)
    }

    override fun addTarea(ca: Categoria, ta: Tarea) {
        return db.conexion.daoTarea().addTarea(ca,ta)
    }

    override fun getTareas(ca: Categoria): MutableList<Tarea> {
        return db.conexion.daoTarea().getTareas(ca)
    }

    override fun getTarea(ta: Tarea): Tarea? {
        return  db.conexion.daoTarea().getTarea(ta)
    }

    override fun updateNombreTarea(ca: Categoria, taAnt: Tarea, taNue: Tarea) {
        return db.conexion.daoTarea().updateNombreTarea(ca,taAnt,taNue)
    }


    override fun deleteTarea(ca: Categoria, ta: Tarea) {
        return db.conexion.daoTarea().deleteTarea(ca,ta)
    }

    override fun addItem(ite: Item) {
        return db.conexion.daoTarea().addItem(ite)
    }

    override fun getItems(id: Int): MutableList<Item> {
        return db.conexion.daoTarea().getItems(id)
    }

    override fun getItem(it: Item): Item? {
        return db.conexion.daoTarea().getItem(it)
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