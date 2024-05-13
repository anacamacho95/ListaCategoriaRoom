package com.example.listacategoriaroom.conexiones

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.listacategoriaroom.entidades.Categoria
import com.example.listacategoriaroom.entidades.Item
import com.example.listacategoriaroom.entidades.Tarea
import com.example.listacategoriaroom.interfaces.InterfaceDaoCategorias
import com.example.listacategoriaroom.interfaces.InterfaceDaoTareas

@Database(entities = [Categoria::class, Tarea::class, Item::class], version = 2)
abstract class BDRoom: RoomDatabase(){

    // Estos métodos abstractos proporcionan acceso al DAO correspondiente
    abstract fun daoCategoria(): InterfaceDaoCategorias
    abstract fun daoTarea(): InterfaceDaoTareas

    companion object {
        var INSTANCE: BDRoom?=null
        fun getBaseDatos(context: Context): BDRoom {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    BDRoom::class.java,
                    "categoriasDB"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                //Con migracion hay que incrementar la version y añadirle que migracion
                /*INSTANCE = Room.databaseBuilder(context.getApplicationContext(),BaseDatos.class,
                    "DietaBD").addMigrations(MIGRATION1_2).allowMainThreadQueries().build();*/
            }
            return INSTANCE as BDRoom
        }
    }

    fun borrarArchivos () {
        val daoCategoria = INSTANCE?.daoCategoria()
        val daoTarea = INSTANCE?.daoTarea()

        // Obtener todas las categorías
        val categorias = daoCategoria?.getCategorias()

        // Eliminar todas las tareas asociadas a cada categoría y luego eliminar la categoría
        categorias?.forEach { categoria ->
            // Obtener todas las tareas asociadas a esta categoría
            val tareas = daoTarea?.getTareas(categoria.idCategoria)

            // Eliminar todas las tareas asociadas a esta categoría
            tareas?.forEach { tarea ->
                // Eliminar todos los items asociados a esta tarea
                val items = daoTarea.getItems(tarea.idTarea)
                items.forEach { item ->
                    daoTarea.deleteItem(item)
                }
                daoTarea.deleteTarea(tarea)
            }

            // Finalmente, eliminar la categoría
            daoCategoria?.deleteCategoria(categoria)
        }
    }

}