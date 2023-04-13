package com.boundinteractive.kmmsample.data

actual class KeyPantry {
    actual fun fetchString(key: String): String? {
        return null
    }

    actual fun saveString(key: String, value: String?): Boolean {
        return true
    }

    actual fun clearAll(): Boolean {
        return true
    }
}