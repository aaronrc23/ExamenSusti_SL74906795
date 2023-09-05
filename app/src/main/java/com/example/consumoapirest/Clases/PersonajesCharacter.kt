package com.example.consumoapirest.Clases

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "personajesCharacter")
@Parcelize
data class PersonajesCharacter(
    @PrimaryKey
    val _id: String,
    val allies: List<String>,
    val enemies: List<@kotlinx.android.parcel.RawValue Any>,
    val name: String?,
    val photoUrl: String
): Parcelable