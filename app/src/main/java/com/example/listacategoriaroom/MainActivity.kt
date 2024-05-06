package com.example.listacategoriaroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listacategoriaroom.conexiones.BDRoom
import com.example.listacategoriaroom.entidades.Categoria
import com.example.listacategoriaroom.entidades.Item
import com.example.listacategoriaroom.entidades.Tarea
import com.example.listacategoriaroom.interfaces.InterfaceDaoCategorias
import com.example.listacategoriaroom.interfaces.InterfaceDaoConexion
import com.example.listacategoriaroom.interfaces.InterfaceDaoTareas

class MainActivity : AppCompatActivity() {
    lateinit var daoCategoria: InterfaceDao
    lateinit var daoTarea: InterfaceDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val conexion= BDRoom(this)
        daoCategoria=InterfaceDao()
        daoTarea=InterfaceDao()
        (daoCategoria).createConexion(conexion)
        (daoTarea as InterfaceDaoConexion).createConexion(conexion)
        //(daoCategoria as InterfaceDao).createConexion(conexion)
        //(daoTarea as InterfaceDao).createConexion(conexion)

        conexion.borrarArchivos()
        Log.d("pruebas", " -- Datos previos eliminados --")

        pruebas()

    }

    fun pruebas(){

        Log.d("pruebas", " *** Añado categorias: hogar y viajes ***")
        var hogar = Categoria("Hogar")
        daoCategoria.addCategoria(hogar)
        var viajes = Categoria("Viajes")
        daoCategoria.addCategoria(viajes)

        Log.d("pruebas", " *** Veo categorias añadidas ***")
        val muestraCategorias1: List<Categoria> = daoCategoria.getCategorias()
        muestraCategorias1.forEach {
            Log.d("pruebas", it.nombre)
        }

        Log.d("pruebas", " *** Añado tareas a la categoria Hogar *** ")
        var cocina = Tarea(daoTarea.getCategoriaId("Hogar"), "Cocina")
        daoTarea.addTarea(cocina)
        var aseo = Tarea(daoTarea.getCategoriaId("Hogar"), "Aseo")
        daoTarea.addTarea(aseo)
        val tareasHogar1: List<Tarea> = daoTarea.getTareas(daoTarea.getCategoriaId("Hogar"))
        tareasHogar1.forEach {
            Log.d("pruebas", it.nombre)
        }

        Log.d("pruebas", " *** Añado items a la tarea Cocina *** ")
        var coc1 = Item(daoTarea.getTareaId("Cocina"), "Hacer canelones", false)
        daoTarea.addItem(coc1)
        val itemsCocina: List<Item> = daoTarea.getItems(daoTarea.getCategoriaId("Hogar"))
        itemsCocina.forEach{
            Log.d("pruebas", it.accion)
        }
        Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(daoTarea.getCategoriaId("Hogar")))


        Log.d("pruebas", " *** Añado tareas a la categoria Viajes *** ")
        var playa = Tarea( daoTarea.getCategoriaId("Viajes") , "Playas")
        daoTarea.addTarea( playa)
        var mont = Tarea( daoTarea.getCategoriaId("Viajes") , "Montañas")
        daoTarea.addTarea( mont)
        val tareasViaje: List<Tarea> = daoTarea.getTareas(daoTarea.getCategoriaId("Viajes"))
        tareasViaje.forEach {
            Log.d("pruebas", it.nombre)
        }


        Log.d("pruebas", " *** Muestro todas las categorias con sus tareas e items *** ")
        val muestraCategorias2: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias2) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(daoTarea.getCategoriaId(categoria.nombre))
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(daoTarea.getTareaId(tarea.nombre))
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(daoTarea.getTareaId(tarea.nombre)))
            }
        }

        Log.d("pruebas", " *** Actualizo item de cocina (Canelones por lavavajillas) y Actualizo nombre de tarea Aseo por Habitación *** ")

        var coc2 = Item(daoTarea.getTareaId("Cocina"),"Poner lavavajillas", false)

        var prue = daoTarea.getTareaId("Cocina")
        Log.d("probando", prue.toString())
        Log.d("probando", coc1.accion)
        Log.d("probando", coc2.accion)

        daoTarea.updateItem(coc2)

        var habitacion = Tarea(daoTarea.getCategoriaId("Hogar"),"Habitacion")
        daoTarea.updateTarea(habitacion)


        /*
        var coc2 = Item(daoTarea.getTareaId("Cocina"),"Poner lavavajillas", false)
        Log.d("pruebas", coc2.accion)
        daoTarea.updateItem(coc1, coc2)

        var habitacion = Tarea(daoTarea.getCategoriaId("Hogar"),"Habitación")
        daoTarea.updateNombreTarea(aseo, habitacion)
        */
        val tareasHogar2: List<Tarea> = daoTarea.getTareas(daoTarea.getCategoriaId("Hogar"))
        for (tarea in tareasHogar2) {
            Log.d("pruebas", "Tarea: ${tarea.nombre}")
            val items: List<Item> = daoTarea.getItems(daoTarea.getTareaId(tarea.nombre))
            for (item in items) {
                Log.d("pruebas", "- ${item.accion}")
            }
            Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(daoTarea.getTareaId(tarea.nombre)))
        }

        Log.d("pruebas", " *** Actualizo nombre de categoria Hogar por Casa *** ")
        var casa= Categoria("Casa")
        daoCategoria.updateCategoria(casa)
        val muestraCategorias3: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias3) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(daoTarea.getCategoriaId(categoria.nombre))
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(daoTarea.getTareaId(tarea.nombre))
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(daoTarea.getTareaId(tarea.nombre)))
            }
        }


        Log.d("pruebas", " *** Elimino item (lavavajillas) de la tarea Cocina *** ")
        daoTarea.deleteItem(coc2)
        val muestraCategorias4: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias4) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(daoTarea.getCategoriaId(categoria.nombre))
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(daoTarea.getTareaId(tarea.nombre))
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(daoTarea.getTareaId(tarea.nombre)))
            }
        }

        Log.d("pruebas", " *** Elimino tarea (Cocina) de la Categoria Casa *** ")
        daoTarea.deleteTarea(cocina)
        val muestraCategorias5: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias5) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(daoTarea.getCategoriaId(categoria.nombre))
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(daoTarea.getTareaId(tarea.nombre))
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(daoTarea.getTareaId(tarea.nombre)))
            }
        }

        Log.d("pruebas", " *** Elimino categoria Casa *** ")
        daoCategoria.deleteCategoria(casa)
        val muestraCategorias6: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias6) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(daoTarea.getCategoriaId(categoria.nombre))
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(daoTarea.getTareaId(tarea.nombre))
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(daoTarea.getTareaId(tarea.nombre)))
            }
        }
    }
}