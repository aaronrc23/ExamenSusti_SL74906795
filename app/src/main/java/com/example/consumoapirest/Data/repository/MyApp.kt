package com.example.consumoapirest.Data.repository

import android.app.Application
import androidx.room.Room
import com.example.consumoapirest.Data.Db.PersonajesCharacterDao
import com.example.consumoapirest.Data.Db.PersonajesDao
import com.example.consumoapirest.Data.Db.PersonajesDatabase

class MyApp : Application() {

    companion object {
        lateinit var database: PersonajesDatabase
        lateinit var personajesDao: PersonajesDao
        lateinit var personajesCharacterDao: PersonajesCharacterDao
    }

    override fun onCreate() {
        super.onCreate()

        // Inicializa la base de datos y los DAOs
        database = Room.databaseBuilder(
            applicationContext,
            PersonajesDatabase::class.java,
            "my_database"
        ).build()

        personajesDao = database.personajesDao()
        personajesCharacterDao = database.personajesCharacterDao()
    }

    /*
    companion object {
        lateinit var database: PersonajesDatabase
        lateinit var personajesDao: PersonajesDao
    }

    override fun onCreate() {
        super.onCreate()

        // Inicializa la base de datos y el DAO
        database = Room.databaseBuilder(
            applicationContext,
            PersonajesDatabase::class.java,
            "my_database"
        ).build()

        personajesDao = database.PersonajesDao()
    }*/
}

