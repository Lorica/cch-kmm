package com.boundinteractive.kmmsample

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateTimeHelper {
    fun String.toFormattedDate(): String {
        val instant = Instant.parse(this)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localDateTime.date}-${localDateTime.month}-${localDateTime.year} " +
                "${localDateTime.hour}:${localDateTime.minute}:${localDateTime.second}"
    }
}