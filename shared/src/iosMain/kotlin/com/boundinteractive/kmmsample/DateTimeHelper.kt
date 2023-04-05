package com.boundinteractive.kmmsample

import kotlinx.datetime.Instant
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual object DateTimeHelper {
    actual fun Instant.asFormattedDate(): String {
        val dateFormatter = NSDateFormatter().apply {
            dateFormat = "dd-MM-yyyy hh:mm"
            locale = NSLocale.currentLocale()
        }
        return dateFormatter.stringFromDate(toNSDate())
    }
}