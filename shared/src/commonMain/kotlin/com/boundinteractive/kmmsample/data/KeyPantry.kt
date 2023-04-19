package com.boundinteractive.kmmsample.data

expect class KeyPantry {
    fun fetchString(key: String): String?
    fun saveString(key: String, value: String?): Boolean
    fun clearAll(): Boolean
}