package com.boundinteractive.kmmsample.data

import platform.Foundation.NSUserDefaults

actual class KeyPantry {
    private val userDefaults = NSUserDefaults.standardUserDefaults

    actual fun fetchString(key: String): String? {
        return userDefaults.stringForKey(key)
    }

    actual fun saveString(key: String, value: String?): Boolean = with (userDefaults) {
        setObject(value, key)
        return synchronize()
    }

    actual fun clearAll(): Boolean {
        return userDefaults.dictionaryRepresentation()
            .keys
            .forEach { key ->
                (key as? String)?.let { strKey ->
                    userDefaults.removeObjectForKey(strKey)
                }
            }.let { true }
    }
}
