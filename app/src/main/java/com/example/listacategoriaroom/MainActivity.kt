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
    lateinit var conexion: BDRoom
    lateinit var daoCategoria: InterfaceDaoCategorias
    lateinit var daoTarea: InterfaceDaoTareas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        conexion= BDRoom.getBaseDatos(this)
        daoCategoria = conexion.daoCategoria()
        daoTarea = conexion.daoTarea()

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

        Log.d("pruebas", " *** Obtengo Categoria Hogar *** ")
        val obtHogar: Categoria = daoCategoria.getCategoria("Hogar")
        Log.d("pruebas", obtHogar.toString())

        Log.d("pruebas", " *** Añado tareas a la categoria Hogar *** ")
        var cocina = Tarea( daoCategoria.getCategoria("Hogar").idCategoria,"Cocina")
        daoTarea.addTarea(cocina)
        var aseo = Tarea( daoCategoria.getCategoria("Hogar").idCategoria,"Aseo")
        daoTarea.addTarea(aseo)
        val tareasHogar1: List<Tarea> = daoTarea.getTareas(daoCategoria.getCategoria("Hogar").idCategoria)
        tareasHogar1.forEach {
            Log.d("pruebas", it.nombre)
        }

        Log.d("pruebas", " *** Obtengo Tarea Cocina *** ")
        val obtCocina: Tarea = daoTarea.getTarea("Cocina")
        Log.d("pruebas", obtCocina.toString())

        Log.d("pruebas", " *** Añado items a la tarea Cocina *** ")
        var coc1 = Item(daoTarea.getTarea("Cocina").idTarea,"Hacer canelones", false)
        daoTarea.addItem(coc1)
        val itemsCocina: List<Item> = daoTarea.getItems(daoTarea.getTarea("Cocina").idTarea)
        itemsCocina.forEach{
            Log.d("pruebas", it.accion)
        }
        Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(daoTarea.getTarea("Cocina").idTarea))

        Log.d("pruebas", " *** Obtengo Item Hacer Canelones *** ")
        val obtCanelones: Item = daoTarea.getItem("Hacer canelones")
        Log.d("pruebas", obtCanelones.toString())

        Log.d("pruebas", " *** Añado tareas a la categoria Viajes *** ")
        var playa = Tarea( daoCategoria.getCategoria("Viajes").idCategoria,"Playas")
        daoTarea.addTarea(playa)
        var mont = Tarea( daoCategoria.getCategoria("Viajes").idCategoria,"Montañas")
        daoTarea.addTarea(mont)
        val tareasViaje: List<Tarea> = daoTarea.getTareas(daoCategoria.getCategoria("Viajes").idCategoria)
        tareasViaje.forEach {
            Log.d("pruebas", it.nombre)
        }

        Log.d("pruebas", " *** Muestro todas las categorias con sus tareas e items *** ")
        val muestraCategorias2: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias2) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria.idCategoria)
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(tarea.idTarea)
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
            }
        }

        Log.d("pruebas", " *** Actualizo item de cocina (Canelones por lavavajillas) y Actualizo nombre de tarea Aseo por Habitación *** ")
        var actualizoItem = daoTarea.getItem("Hacer canelones")
        actualizoItem.accion="Poner lavavajillas"
        daoTarea.updateItem(actualizoItem)

        var actualizoTarea = daoTarea.getTarea("Aseo")
            actualizoTarea.nombre="Habitación"
        daoTarea.updateNombreTarea(actualizoTarea)

        val tareasHogar2: List<Tarea> = daoTarea.getTareas(daoCategoria.getCategoria("Hogar").idCategoria)
        for (tarea in tareasHogar2) {
            Log.d("pruebas", "Tarea: ${tarea.nombre}")
            val items: List<Item> = daoTarea.getItems(tarea.idTarea)
            for (item in items) {
                Log.d("pruebas", "- ${item.accion}")
            }
            Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
        }

        Log.d("pruebas", " *** Actualizo nombre de categoria Hogar por Casa *** ")
        var actualizoCategoria= daoCategoria.getCategoria("Hogar")
        actualizoCategoria.nombre="Casa"
        daoCategoria.updateCategoria(actualizoCategoria)

        val muestraCategorias3: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias3) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria.idCategoria)
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(tarea.idTarea)
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
            }
        }

        Log.d("pruebas", " *** Elimino item (lavavajillas) de la tarea Cocina *** ")
        daoTarea.deleteItem(daoTarea.getItem("Poner lavavajillas"))
        val muestraCategorias4: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias4) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria.idCategoria)
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(tarea.idTarea)
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
            }
        }

        Log.d("pruebas", " *** Elimino tarea (Cocina) de la Categoria Casa *** ")
        daoTarea.deleteTarea(daoTarea.getTarea("Cocina"))
        val muestraCategorias5: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias5) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria.idCategoria)
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(tarea.idTarea)
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
            }
        }

        Log.d("pruebas", " *** Elimino categoria Casa *** ")
        daoCategoria.deleteCategoria(daoCategoria.getCategoria("Casa"))
        val muestraCategorias6: List<Categoria> = daoCategoria.getCategorias()
        for (categoria in muestraCategorias6) {
            Log.d("pruebas", "Categoría --> ${categoria.nombre}")

            val tareas: List<Tarea> = daoTarea.getTareas(categoria.idCategoria)
            for (tarea in tareas) {
                Log.d("pruebas", "Tarea: ${tarea.nombre}")

                val items: List<Item> = daoTarea.getItems(tarea.idTarea)
                for (item in items) {
                    Log.d("pruebas", "- ${item.accion}")
                }
                Log.d("pruebas", "NºTareas: "+ daoTarea.getNItems(tarea.idTarea))
            }
        }
    }
}