package com.example.consumoapirest.View

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumoapirest.Clases.Personajes
import com.example.consumoapirest.Data.Db.PersonajesDao
import kotlinx.coroutines.launch

class FavoriteViewModel(private val personajesDao: PersonajesDao): ViewModel() {

    val favoritePersonajes: LiveData<List<Personajes>> = personajesDao.getAllFavoritePersonajes()

    fun deletePersonaje(personajeId: String) {
        viewModelScope.launch {
            personajesDao.deletePersonaje(personajeId)
        }
    }
}