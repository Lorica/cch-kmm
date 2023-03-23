package com.boundinteractive.kmmsample.data

import android.content.Context

actual class KeyPantry(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)

    /*actual fun fetchFavourites(): List<Int> {
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
    }*/

    actual fun fetchString(key: String): String? {
        return sharedPreferences
            .getString(key, null)
    }

    actual fun saveString(key: String, value: String) {
        sharedPreferences
            .edit()
            .putString(key, value)
            .apply()
    }

    actual fun saveIntArray(key: String, value: Array<Int>) {
        val arrayString = value.joinToString(",")
        saveString(key, arrayString)
    }

    actual fun fetchIntArray(key: String): Array<Int> {
        return sharedPreferences
            .getString(key, null)
            ?.split(",")
            ?.map { it.toInt() }
            ?.toTypedArray()
            ?: emptyArray()
    }
}