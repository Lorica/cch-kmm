package com.boundinteractive.kmmsample

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

actual object DateTimeHelper {
    actual fun Instant.asFormattedDate(): String {
        val datetime = toLocalDateTime(TimeZone.currentSystemDefault())
        return DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")
            .format(datetime.toJavaLocalDateTime())
    }
}