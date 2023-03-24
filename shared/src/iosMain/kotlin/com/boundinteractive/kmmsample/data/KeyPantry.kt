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
        userDefaults.apply {
            setObject(value, key)
            synchronize()
        }
    }

    actual fun fetchIntArray(key: String): Array<Int> {
        return userDefaults.arrayForKey(key) as? Array<Int>? ?: emptyArray()
    }

    actual fun clearAll() {
        val domain = NSBundle.mainBundle.bundleIdentifier ?: ""
        userDefaults.apply {
            removePersistentDomainForName(domain)
            synchronize()
        }
    }
}
