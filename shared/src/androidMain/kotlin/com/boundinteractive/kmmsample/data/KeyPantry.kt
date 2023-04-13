package com.boundinteractive.kmmsample.data

import android.content.Context

actual class KeyPantry(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)

    actual fun fetchString(key: String): String? {
        return sharedPreferences
            .getString(key, null)
    }

    actual fun saveString(key: String, value: String?) {
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

    actual fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}