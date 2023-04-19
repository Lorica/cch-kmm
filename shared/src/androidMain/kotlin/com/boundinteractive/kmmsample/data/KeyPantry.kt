package com.boundinteractive.kmmsample.data

import android.content.Context

actual class KeyPantry(context: Context) {
    private val sharedPreferences = context
        .getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)

    actual fun fetchString(key: String): String? = sharedPreferences.getString(key, null)

    actual fun saveString(key: String, value: String?): Boolean = sharedPreferences
        .edit()
        .putString(key, value)
        .commit()

    actual fun clearAll(): Boolean = sharedPreferences.edit().clear().commit()
}