package com.boundinteractive.kmmsample.data

actual class KeyPantry {
    actual fun fetchString(key: String): String? {
        return null
    }

    actual fun saveString(key: String, value: String?) {
        /* no-op */
    }

    actual fun saveIntArray(key: String, value: Array<Int>) {
        /* no-op */
    }

    actual fun fetchIntArray(key: String): Array<Int> {
        return emptyArray()
    }

    actual fun clearAll() {
        /* no-op */
    }
}