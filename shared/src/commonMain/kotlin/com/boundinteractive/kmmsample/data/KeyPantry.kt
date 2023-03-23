package com.boundinteractive.kmmsample.data

expect class KeyPantry {
    fun fetchFavourites(): List<Int>
    fun saveFavourites(id: Int)
    fun removeFavourites(id: Int)
}