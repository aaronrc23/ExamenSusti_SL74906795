package com.example.consumoapirest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.consumoapirest.Clases.Personajes
import com.example.consumoapirest.Clases.PersonajesCharacter
import com.example.consumoapirest.R

class PersonajeAdapterCharacter(private var dataList1: List<PersonajesCharacter>): RecyclerView.Adapter<PersonajeAdapterCharacter.PersonajesCharaterViewHolder>() {

    var onItemClick: ((PersonajesCharacter) -> Unit)? = null //card

    class PersonajesCharaterViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.img_fav)
        val productNameTextView: TextView = itemView.findViewById(R.id.txtcfname)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonajesCharaterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_card_fav, parent, false)
        return PersonajeAdapterCharacter.PersonajesCharaterViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList1.size
    }

    override fun onBindViewHolder(holder: PersonajesCharaterViewHolder, position: Int) {
        val product = dataList1[position]


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
    fun setData(personaje1: List<PersonajesCharacter>) {
        dataList1 = personaje1
        notifyDataSetChanged()
    }


}