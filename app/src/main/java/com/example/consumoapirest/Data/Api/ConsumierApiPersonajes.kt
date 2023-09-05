package com.example.consumoapirest.Data.Api

import com.example.consumoapirest.Clases.Personajes
import com.example.consumoapirest.Clases.PersonajesCharacter
import retrofit2.Call
import retrofit2.http.GET

interface ConsumierApiPersonajes {

    @GET("characters/random?count=30")
    fun getTraer(): Call<List<Personajes>>


    @GET("characters")
    fun getPersonChar(): Call<List<PersonajesCharacter>>

}