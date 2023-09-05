package com.example.consumoapirest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.consumoapirest.Clases.Personajes
import com.example.consumoapirest.Data.Db.PersonajesDao
import com.example.consumoapirest.R
import com.example.consumoapirest.View.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class favoriteAdapter (private var persondata: List<Personajes>, private val favoriteViewModel: FavoriteViewModel):
    RecyclerView.Adapter<favoriteAdapter.favoritoViewHolder>(){

    class favoritoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.imgcategoria)
        val productNameTextView: TextView = itemView.findViewById(R.id.txtcattitle)
        val btnEliminarpersonaje: Button = itemView.findViewById(R.id.btn_eliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favoritoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_personajes, parent, false)
        return favoritoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return persondata.size
    }


    override fun onBindViewHolder(holder: favoritoViewHolder, position: Int) {
        val person = persondata[position]
        Glide.with(holder.itemView.context)
            .load(person.photoUrl)
            .into(holder.productImage)
        holder.productNameTextView.text = "${person.name}"
        holder.btnEliminarpersonaje.setOnClickListener {
            eliminarPersonaje(person)
        }

    }
    fun setData(personajes: List<Personajes>) {
        persondata = personajes
        notifyDataSetChanged()
    }

    private fun eliminarPersonaje(personaje: Personajes) {
        favoriteViewModel.deletePersonaje(personaje._id)
    }


}