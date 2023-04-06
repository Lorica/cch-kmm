package com.boundinteractive.kmmsample

import kotlinx.datetime.Instant

actual object DateTimeHelper {
    actual fun Instant.asFormattedDate(): String {
        /* no-op */
        return ""
    }
}