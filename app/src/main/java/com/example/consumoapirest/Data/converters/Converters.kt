package com.example.consumoapirest.Data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toStringList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromAnyList(value: String): List<Any> {
        val listType = object : TypeToken<List<Any>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toAnyList(list: List<Any>): String {
        return Gson().toJson(list)
    }

   /* @TypeConverter
    fun fromStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toStringList(list: List<String>): String {
        return Gson().toJson(list)
    }*/
}