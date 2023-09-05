package com.example.consumoapirest.Data.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.consumoapirest.Clases.Personajes
import com.example.consumoapirest.Clases.PersonajesCharacter
import com.example.consumoapirest.Data.converters.Converters


@Database(entities=[Personajes::class,PersonajesCharacter::class],version=1)
@TypeConverters(Converters::class) // Agregar esta línea
abstract class PersonajesDatabase :RoomDatabase() {
    abstract fun personajesDao(): PersonajesDao
    abstract fun personajesCharacterDao(): PersonajesCharacterDao

    companion object {
        @Volatile
        private var INSTANCE: PersonajesDatabase? = null

        fun getDatabase(context: Context): PersonajesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonajesDatabase::class.java,
                    "personajes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}