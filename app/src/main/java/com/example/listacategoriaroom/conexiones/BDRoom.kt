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

class BDRoom (private val context: Context){
    val conexion= BaseDatos.getBaseDatos(context)

    fun borrarArchivos(){
        // Cierra la conexión de la base de datos antes de borrar los archivos
        conexion.close()

        // Borra la base de datos
        context.deleteDatabase("categoriasDB")

        // Vuelve a crear la base de datos
        BaseDatos.INSTANCE = null
        BaseDatos.getBaseDatos(context)
    }
}
@Database(entities = [Categoria::class, Tarea::class, Item::class], version = 1)
abstract class BaseDatos: RoomDatabase(){

    abstract fun daoCategoria(): InterfaceDaoCategorias
    abstract fun daoTarea(): InterfaceDaoTareas

    companion object {
        var INSTANCE: BaseDatos?=null
        fun getBaseDatos(context: Context): BaseDatos {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDatos::class.java,
                    "categoriasDB"
                ).allowMainThreadQueries().build()
                //Con migracion hay que incrementar la version y añadirle que migracion
                /*INSTANCE = Room.databaseBuilder(context.getApplicationContext(),BaseDatos.class,
                    "DietaBD").addMigrations(MIGRATION1_2).allowMainThreadQueries().build();*/
            }
            return INSTANCE as BaseDatos
        }
    }
}