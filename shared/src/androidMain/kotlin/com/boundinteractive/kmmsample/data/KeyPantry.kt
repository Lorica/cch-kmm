package com.boundinteractive.kmmsample.data

import android.content.Context

actual class KeyPantry(context: Context) {
    companion object {
        private const val FAVOURITES_KEY = "favourites"
    }
    private val sharedPreferences =
        context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)

    actual fun fetchFavourites(): List<Int> {
        // Retrieve the serialized string from SharedPreferences
        val serializedList = sharedPreferences.getString(FAVOURITES_KEY, null)

        // Convert the string back to a list of integers
        return serializedList?.split(",")?.map { it.toInt() } ?: emptyList()
    }

    actual fun saveFavourites(id: Int) {
        val favourites = fetchFavourites().toMutableList()

        favourites.add(id)

        // Save the serialized string to SharedPreferences
        sharedPreferences.edit().putString(FAVOURITES_KEY, favourites.joinToString(",")).apply()
    }

    actual fun removeFavourites(id: Int) {
        val favourites = fetchFavourites().toMutableList()

        favourites.remove(id)

        // Save the serialized string to SharedPreferences
        sharedPreferences.edit().putString(FAVOURITES_KEY, favourites.joinToString(",")).apply()
    }
}