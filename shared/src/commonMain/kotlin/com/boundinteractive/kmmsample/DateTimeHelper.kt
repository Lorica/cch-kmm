package com.boundinteractive.kmmsample

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateTimeHelper {
    fun String.toFormattedDate(resultTimeZone: TimeZone): String {
        val instant = Instant.parse(this)
        val localDateTime = instant.toLocalDateTime(resultTimeZone)
        return localDateTime.dayOfMonth.toString().padStart(2, '0') +
                "-" +
                localDateTime.monthNumber.toString().padStart(2, '0') +
                "-" +
                localDateTime.year +
                " " +
                localDateTime.hour.toString().padStart(2, '0') +
                ":" +
                localDateTime.minute.toString().padStart(2, '0')
    }
}