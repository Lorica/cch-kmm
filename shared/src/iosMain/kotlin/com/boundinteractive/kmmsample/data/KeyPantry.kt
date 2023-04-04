package com.boundinteractive.kmmsample.data

import platform.Foundation.NSBundle
import platform.Foundation.NSUserDefaults

actual class KeyPantry {
    private val userDefaults = NSUserDefaults.standardUserDefaults

    actual fun fetchString(key: String): String? {
        return userDefaults.stringForKey(key)
    }

    actual fun saveString(key: String, value: String) {
        userDefaults.apply {
            setObject(value, key)
            synchronize()
        }
    }

    actual fun saveIntArray(key: String, value: Array<Int>) {
        val arrayString = value.joinToString(",")
        saveString(key, arrayString)
    }

    actual fun fetchIntArray(key: String): Array<Int> {
        return fetchString(key)
            ?.split(",")
            ?.map { it.toInt() }
            ?.toTypedArray()
            ?: emptyArray()
    }

    actual fun clearAll() {
        val domain = NSBundle.mainBundle.bundleIdentifier ?: ""
        userDefaults.apply {
            removePersistentDomainForName(domain)
            synchronize()
        }
    }
}
