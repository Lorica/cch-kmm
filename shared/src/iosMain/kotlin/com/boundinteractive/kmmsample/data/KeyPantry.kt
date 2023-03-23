package com.boundinteractive.kmmsample.data

import platform.Foundation.NSNumber
import platform.Foundation.NSUserDefaults
import platform.Foundation.numberWithInt

actual class KeyPantry(private val userDefaults: NSUserDefaults) {

    companion object {
        private const val FAVOURITES_KEY = "favourites"
    }

    actual fun fetchFavourites(): List<Int> {
        // Retrieve the array from UserDefaults
        val array = userDefaults.arrayForKey(FAVOURITES_KEY) as? Array<NSNumber>

        // Convert the array of NSNumbers to a list of integers
        return array?.map { it.intValue() } ?: emptyList()
    }

    actual fun saveFavourites(id: Int) {
        val favourites = fetchFavourites().toMutableList()

        favourites.add(id)

        // Convert the list of integers to an array of NSNumbers
        val array = favourites.map { NSNumber.numberWithInt(it) }.toTypedArray()

        // Save the array to UserDefaults
        userDefaults.setObject(array, forKey = FAVOURITES_KEY)
    }

    actual fun removeFavourites(id: Int) {
        val favourites = fetchFavourites().toMutableList()

        favourites.remove(id)

        // Convert the list of integers to an array of NSNumbers
        val array = favourites.map { NSNumber.numberWithInt(it) }.toTypedArray()

        // Save the array to UserDefaults
        userDefaults.setObject(array, forKey = FAVOURITES_KEY)
    }
}
