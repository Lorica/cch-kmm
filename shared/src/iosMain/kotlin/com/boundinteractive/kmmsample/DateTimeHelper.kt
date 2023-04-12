package com.boundinteractive.kmmsample

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.currentLocale
import platform.Foundation.defaultTimeZone

actual object DateTimeHelper {
    actual fun Instant.asFormattedDate(): String {
        val dateFormatter = NSDateFormatter().apply {
            dateFormat = "dd-MM-yyyy hh:mm"
            locale = NSLocale.currentLocale()
            timeZone = NSTimeZone.defaultTimeZone()
        }
        return dateFormatter.stringFromDate(toNSDate())
    }
}