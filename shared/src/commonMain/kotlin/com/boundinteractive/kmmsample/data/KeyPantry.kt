package com.boundinteractive.kmmsample.data

expect class KeyPantry {
    fun fetchString(key: String): String?
    fun saveString(key: String, value: String?)
    fun saveIntArray(key: String, value: Array<Int>)
    fun fetchIntArray(key: String): Array<Int>
    fun clearAll()
}