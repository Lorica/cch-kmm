import com.boundinteractive.kmmsample.DateTimeHelper.asFormattedDate
import kotlinx.datetime.toInstant
import platform.Foundation.NSTimeZone
import platform.Foundation.defaultTimeZone
import platform.Foundation.timeZoneForSecondsFromGMT
import kotlin.test.Test
import kotlin.test.assertEquals

class DateTimeHelperTest {
    @Test
    fun test_asFormattedDate() {
        NSTimeZone.defaultTimeZone = NSTimeZone.timeZoneForSecondsFromGMT(0)
        val inputInstant = "2013-05-11T02:31:05Z".toInstant()
        val formattedDateOutput = inputInstant.asFormattedDate()
        assertEquals("11-05-2013 02:31", formattedDateOutput)
    }
}