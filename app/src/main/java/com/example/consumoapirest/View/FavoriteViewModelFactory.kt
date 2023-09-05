package com.example.consumoapirest.View

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.consumoapirest.Data.Db.PersonajesDao

class FavoriteViewModelFactory(private val personajesDao: PersonajesDao) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewModel(personajesDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}