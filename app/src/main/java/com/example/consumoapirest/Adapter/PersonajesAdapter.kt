package com.example.consumoapirest.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.consumoapirest.Clases.Personajes
import com.example.consumoapirest.R

class PersonajesAdapter (private var dataList: List<Personajes>): RecyclerView.Adapter<PersonajesAdapter.PersonajesViewHolder>(){

    var onItemClick: ((Personajes) -> Unit)? = null //card

    class PersonajesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val productImage: ImageView = itemView.findViewById(R.id.img_fav)
        val productNameTextView: TextView = itemView.findViewById(R.id.txtcfname)
        //val productgender: TextView = itemView.findViewById(R.id.txtcagender)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_card_fav, parent, false)
        return PersonajesViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: PersonajesViewHolder, position: Int) {
        val product = dataList[position]


        Glide.with(holder.itemView.context)
            .load(product.photoUrl)
            .into(holder.productImage)
        holder.productNameTextView.text = product.name
        //holder.productgender.text = product.gender

        //este para set
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(product)  //recordar cambiar product
        }
    }
    fun setData(personaje: List<Personajes>) {
        dataList = personaje
        notifyDataSetChanged()
    }

}