package com.boundinteractive.kmmsample.util

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object JsonUtil {
    val json = Json {
        ignoreUnknownKeys = true
    }

    inline fun <reified T> T.toJson() = json.encodeToString(this)

    inline fun <reified T> String.decodeFromString() = json.decodeFromString<T>(this)
}