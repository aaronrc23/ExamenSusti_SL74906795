package com.example.consumoapirest.View

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import com.example.consumoapirest.R

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumoapirest.Adapter.favoriteAdapter
import com.example.consumoapirest.Data.Db.PersonajesDao

import com.example.consumoapirest.Data.repository.MyApp

import com.example.consumoapirest.databinding.FragmentFavoritoBinding

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager


class FavoritoFragment : Fragment() {

    private lateinit var binding: FragmentFavoritoBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var miAdapter: favoriteAdapter

    private lateinit var personajesDao: PersonajesDao
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoritoBinding.inflate(inflater, container, false)
        val view = binding.root

        //personajesDao = MyApp.database.PersonajesDao()
        personajesDao = MyApp.personajesDao

        val viewModelFactory = FavoriteViewModelFactory(personajesDao)
        favoriteViewModel = ViewModelProvider(this, viewModelFactory).get(FavoriteViewModel::class.java)

        val rcvEjm2 = view.findViewById<RecyclerView>(R.id.rcvlif)

        val numberOfColumns = 2
        val layoutManager = GridLayoutManager(requireContext(), numberOfColumns, GridLayoutManager.VERTICAL, false)
        rcvEjm2.layoutManager = layoutManager


        // Observa el LiveData y actualiza el RecyclerView
        favoriteViewModel.favoritePersonajes.observe(viewLifecycleOwner, Observer { usuarios ->
            miAdapter = favoriteAdapter(usuarios, favoriteViewModel)
            rcvEjm2.adapter = miAdapter
        })
        return view

    }





}