package com.example.consumoapirest.Data.Db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.consumoapirest.Clases.Personajes
import com.example.consumoapirest.Clases.PersonajesCharacter

@Dao
interface PersonajesDao {


    @Insert
    suspend fun insert(character: Personajes)



    @Delete
    suspend fun deleteUsuario(personaje: Personajes)



    @Query("DELETE FROM personajes WHERE _id = :personajeId")
    suspend fun deletePersonaje(personajeId: String)




    @Query("SELECT * FROM personajes")
    fun getAllFavoritePersonajes(): LiveData<List<Personajes>>




}