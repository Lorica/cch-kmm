package com.boundinteractive.kmmsample

import kotlinx.datetime.Instant

expect object DateTimeHelper {
    fun Instant.asFormattedDate(): String
}