package com.example.consumoapirest.Data.Db

import androidx.room.Dao
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.example.consumoapirest.Clases.PersonajesCharacter

@Dao
interface PersonajesCharacterDao {

    @Insert
    suspend fun insert(personajeCharacter: PersonajesCharacter)

    @Query("SELECT * FROM personajesCharacter")
    fun getAllFavoritePersonajesCharacter(): LiveData<List<PersonajesCharacter>>

    @Query("DELETE FROM personajesCharacter WHERE _id = :personajeId")
    suspend fun deletePersonaje1(personajeId: String)
    @Query("SELECT * FROM personajesCharacter")
    fun getAllFavoritePersonajes1(): LiveData<List<PersonajesCharacter>>
}