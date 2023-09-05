package com.example.consumoapirest.Data
import com.example.consumoapirest.Data.Api.ConsumierApiPersonajes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetorfitPersonajes {
    private val retrofit = Retrofit.Builder()

        .baseUrl("https://last-airbender-api.fly.dev/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getConsumirApiPersonajesService():ConsumierApiPersonajes=retrofit.create(ConsumierApiPersonajes::class.java)

}