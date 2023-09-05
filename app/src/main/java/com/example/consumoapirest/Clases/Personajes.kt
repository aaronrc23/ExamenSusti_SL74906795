package com.example.consumoapirest.Clases
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "personajes")
@Parcelize
data class Personajes(

    @PrimaryKey
    val _id: String,
    val affiliation: String?,
    val allies: List<String>?,
    val enemies: List<@kotlinx.android.parcel.RawValue Any>,
    val name: String?,
    val gender: String?,
    val position: String?,
    val photoUrl: String
): Parcelable
