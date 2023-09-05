package com.example.consumoapirest.View

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.consumoapirest.Adapter.PersonajeAdapterCharacter
import com.example.consumoapirest.Adapter.PersonajesAdapter
import com.example.consumoapirest.Clases.Personajes
import com.example.consumoapirest.Clases.PersonajesCharacter
import com.example.consumoapirest.Data.RetorfitPersonajes
import com.example.consumoapirest.DetalleActivity
import com.example.consumoapirest.R
import com.example.consumoapirest.databinding.FragmentListadoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListadoFragment : Fragment() {

    private lateinit var binding: FragmentListadoBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var miAdapter: PersonajesAdapter
    private lateinit var miAdapter1: PersonajeAdapterCharacter
    private val productList = mutableListOf<Personajes>()
    private val productList1 = mutableListOf<PersonajesCharacter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_listado, container, false)

        // Configurar el RecyclerView rcvli para mostrar Personajes
        /*val recyclerView1 = view.findViewById<RecyclerView>(R.id.rcvli)
        val layoutManager1 = LinearLayoutManager(activity)
        layoutManager1.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView1.layoutManager = layoutManager1
        miAdapter = PersonajesAdapter(productList)
        recyclerView1.adapter = miAdapter


        // Configurar el RecyclerView rcyperson para mostrar PersonajesCharacter
        val recyclerView2 = view.findViewById<RecyclerView>(R.id.rcyperson)
        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView2.layoutManager = layoutManager2
        miAdapter1 = PersonajeAdapterCharacter(productList1)
        recyclerView2.adapter = miAdapter1

        return view*/
        recyclerView = view.findViewById(R.id.rcvli)

        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManager



        miAdapter = PersonajesAdapter(productList)
        recyclerView.adapter = miAdapter

        recyclerView.adapter = miAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        obtenerProductos()


        // Configurar el listener para manejar el clic en un elemento de la lista
        miAdapter.onItemClick = { personaje ->
            val intent = Intent(activity, DetalleActivity::class.java)
            intent.putExtra("personaje", personaje)
            startActivity(intent)
        }
        /*
        miAdapter1.onItemClick = { personajeCharacter ->
            val intent = Intent(activity, DetalleActivity::class.java)
            intent.putExtra("personajeCharacter", personajeCharacter)
            startActivity(intent)
        }*/
    }

    private fun obtenerProductos() {
        val retrofitTraer = RetorfitPersonajes.getConsumirApiPersonajesService().getTraer()

        retrofitTraer.enqueue(object : Callback<List<Personajes>> {
            override fun onResponse(call: Call<List<Personajes>>, response: Response<List<Personajes>>) {
                if (response.isSuccessful) {
                    val productos = response.body()
                    if (productos != null) {
                        miAdapter.setData(productos)
                    } else {
                        Toast.makeText(activity, "No se encontraron personajes.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity, "Error al obtener los personajes.", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Personajes>>, t: Throwable) {
                Toast.makeText(activity, "Error al consultar API Rest", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun obtenerPersonajes() {
        val retrofitTraer = RetorfitPersonajes.getConsumirApiPersonajesService().getPersonChar()

        retrofitTraer.enqueue(object : Callback<List<PersonajesCharacter>> {
            override fun onResponse(call: Call<List<PersonajesCharacter>>, response: Response<List<PersonajesCharacter>>) {
                if (response.isSuccessful) {
                    val productos = response.body()
                    if (productos != null) {
                        // Actualiza los datos del segundo RecyclerView
                        miAdapter1.setData(productos)
                    } else {
                        Toast.makeText(activity, "No se encontraron personajes.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity, "Error al obtener los personajes.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<PersonajesCharacter>>, t: Throwable) {
                Toast.makeText(activity, "Error al consultar API Rest", Toast.LENGTH_SHORT).show()
            }
        })
    }
}