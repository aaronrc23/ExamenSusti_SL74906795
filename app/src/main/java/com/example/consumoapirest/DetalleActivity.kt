package com.example.consumoapirest

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.consumoapirest.Clases.Personajes
import com.example.consumoapirest.Clases.PersonajesCharacter
import com.example.consumoapirest.Data.repository.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetalleActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

                val personaje: Personajes? = intent.getParcelableExtra("personaje")
                val personajeCharacter = intent.getParcelableExtra<PersonajesCharacter>("personajeCharacter")


                val btnAddFavorite: Button = findViewById(R.id.btn_add_favorite)
                btnAddFavorite.setOnClickListener {
                    if (personaje != null) {
                        val favoriteCharacter = Personajes(
                            _id = personaje._id,
                            name = personaje.name,
                            photoUrl = personaje.photoUrl,
                            gender = personaje.gender,
                            position = personaje.position ?: "",
                            affiliation = personaje.affiliation ?: "",
                            allies = emptyList(),
                            enemies = emptyList(),

                        )

                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                MyApp.personajesDao.insert(favoriteCharacter)
                                runOnUiThread {
                                    Toast.makeText(this@DetalleActivity, "Personaje agregado a favoritos", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                runOnUiThread {
                                    Toast.makeText(this@DetalleActivity, "Error al agregar el personaje a favoritos", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }




        try {
            val personaje: Personajes? = intent.getParcelableExtra("personaje")
            val personajeCharacter: PersonajesCharacter? = intent.getParcelableExtra("personajeCharacter")

            if (personaje != null) {
                // Código para mostrar la información de 'Personajes'
                val txtname: TextView = findViewById(R.id.txt1name)
                val txtAffiliation: TextView = findViewById(R.id.txt1allies)
                val txtenemigos: TextView = findViewById(R.id.txt1enemies)
                val txtgenero: TextView = findViewById(R.id.gender)
                val txtposition: TextView = findViewById(R.id.txtposition)
                val imageView: ImageView = findViewById(R.id.img_detalle)

                txtname.text = personaje.name
                txtAffiliation.text = personaje.affiliation
                txtenemigos.text = personaje.enemies.joinToString(", ")
                txtgenero.text = personaje.gender
                txtposition.text = personaje.position

                Glide.with(this)
                    .load(personaje.photoUrl)
                    .into(imageView)
            } else if (personajeCharacter != null) {
                // Código para mostrar la información de 'PersonajesCharacter'
                val txtname: TextView = findViewById(R.id.txt1name)
                val txtAllies: TextView = findViewById(R.id.txt1allies) // Ajusta el ID según tu diseño
                val txtEnemies: TextView = findViewById(R.id.txt1enemies) // Ajusta el ID según tu diseño
                val imageView: ImageView = findViewById(R.id.img_detalle)

                txtname.text = personajeCharacter.name
                txtAllies.text = personajeCharacter.allies.joinToString(", ")
                txtEnemies.text = personajeCharacter.enemies.joinToString(", ")

                Glide.with(this)
                    .load(personajeCharacter.photoUrl)
                    .into(imageView)
            } else {
                Toast.makeText(this, "No se pudo obtener la información del personaje", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error al mostrar la información del personaje", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

/*
        try {
            val personaje: Personajes? = intent.getParcelableExtra("personaje")
            val personajeCharacter = intent.getParcelableExtra<PersonajesCharacter>("personajeCharacter")


            if (personaje != null) {
                val txtname: TextView = findViewById(R.id.txt1name)
                val txtAffiliation: TextView = findViewById(R.id.txt1allies)
                val txtenemigos: TextView = findViewById(R.id.txt1enemies)
                val txtgenero: TextView = findViewById(R.id.gender)
                val txtposition: TextView = findViewById(R.id.txtposition)
                val imageView: ImageView = findViewById(R.id.img_detalle)

                txtname.text = personaje.name
                txtAffiliation.text = personaje.affiliation
                txtenemigos.text = personaje.enemies.joinToString(", ")
                txtgenero.text = personaje.gender
                txtposition.text = personaje.position

                Glide.with(this)
                    .load(personaje.photoUrl)
                    .into(imageView)
            } else {
                Toast.makeText(this, "No se pudo obtener la información del personaje", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error al mostrar la información del personaje", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }*/

        /*
        var like = false

        val lottieLikeAnimation: LottieAnimationView = findViewById(R.id.favoritolike)
        lottieLikeAnimation.setOnClickListener {
            like = likeAnimation(lottieLikeAnimation, R.raw.always_proud, like)
        }*/

    }
/*
    private fun likeAnimation(imageView: LottieAnimationView,
                              animation: Int,
                              like: Boolean) : Boolean {
        if (!like) {
            imageView.setAnimation(animation)
            imageView.repeatCount= 5
            imageView.playAnimation()
        } else {
            imageView.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animator: Animator) {
                        imageView.setImageResource(R.drawable.baseline_star_outline_24)
                        imageView.alpha = 1f
                    }
                })
        }
        return !like
    }*/




}