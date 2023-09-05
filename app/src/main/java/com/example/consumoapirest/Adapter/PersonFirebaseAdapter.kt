package com.example.consumoapirest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.consumoapirest.Clases.PersonFirebase
import com.example.consumoapirest.R


class PersonFirebaseAdapter (private val dataList: List<PersonFirebase>) :
    RecyclerView.Adapter<PersonFirebaseAdapter.ViewHolder>(){
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.txtfname)
        val affilitiationTextView: TextView = itemView.findViewById(R.id.txtaffilitiation)
        val aliadoTextView: TextView=itemView.findViewById(R.id.txtfaliado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_pers, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() : Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.nameTextView.text = currentItem.name
        holder.affilitiationTextView.text = currentItem.affiliation
        holder.aliadoTextView.text = currentItem.allies
    }

}