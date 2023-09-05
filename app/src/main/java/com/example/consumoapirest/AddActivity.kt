package com.example.consumoapirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.consumoapirest.databinding.ActivityAddBinding
import com.example.consumoapirest.databinding.ActivityMainBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa la instancia de FirebaseFirestore
        firestore = Firebase.firestore
        //firestore = FirebaseFirestore.firestore

        binding.btnAgregarF.setOnClickListener {

            val name: String = binding.txtpnombre.editText?.text.toString()
            val affiliation: String = binding.txtpaffilition.editText?.text.toString()
            val enemies: String = binding.txtenemigo.editText?.text.toString()
            val allies: String = binding.txtaliado.editText?.text.toString()

            if(name.isNotEmpty() && affiliation.isNotEmpty()&&enemies.isNotEmpty()&&allies.isNotEmpty()){
                val personajes= hashMapOf(
                    "affiliation" to affiliation,
                    "allies" to allies,
                    "enemies" to enemies,
                    "name" to name,
                    "photoUrl" to "",
                    "date" to Timestamp.now()
                )
                firestore.collection("Personajes")
                    .add(personajes)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Agregado correctamente con id: ${it.id}", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "No se agrego el elemento", Toast.LENGTH_SHORT).show()
                    }
            }
        }


    }
}