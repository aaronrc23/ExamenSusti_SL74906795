package com.example.consumoapirest.View

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumoapirest.Adapter.PersonFirebaseAdapter
import com.example.consumoapirest.AddActivity
import com.example.consumoapirest.Clases.PersonFirebase
import com.example.consumoapirest.R
import com.example.consumoapirest.databinding.FragmentFirebaseBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseFragment : Fragment() {


    private lateinit var firestore: FirebaseFirestore
    private lateinit var binding: FragmentFirebaseBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFirebaseBinding.inflate(inflater,container,false)

        binding.addButton.setOnClickListener {
            val intent = Intent(activity, AddActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firestore = Firebase.firestore

        val dataList = mutableListOf<PersonFirebase>()
        val adapter = PersonFirebaseAdapter(dataList)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rcyfirebase.layoutManager = layoutManager
        binding.rcyfirebase.adapter = adapter

        firestore.collection("Personajes")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val item = document.toObject(PersonFirebase::class.java)
                    dataList.add(item)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->

            }
    }

}