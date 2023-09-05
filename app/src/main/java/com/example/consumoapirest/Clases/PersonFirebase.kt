package com.example.consumoapirest.Clases

data class PersonFirebase(
    val affiliation: String="",
    val allies: String="",
    val enemies: String="",
    val name: String="",
    val photoUrl: String=""
)
{
    constructor() : this("", "", "", "", "")
}
