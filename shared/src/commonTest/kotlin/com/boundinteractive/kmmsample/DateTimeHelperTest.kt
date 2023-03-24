package com.boundinteractive.kmmsample

import com.boundinteractive.kmmsample.DateTimeHelper.toFormattedDate
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

class DateTimeHelperTest {

    @Test
    fun `Given a properly formatted String date When formatted Then expected format is output`() {
        val output = "2013-05-11T02:31:05Z".toFormattedDate(TimeZone.UTC)
        assertEquals("11-05-2013 02:31", output)
    }
}