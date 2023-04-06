import com.boundinteractive.kmmsample.DateTimeHelper.asFormattedDate
import kotlinx.datetime.toInstant
import org.junit.Test
import java.time.ZoneId
import java.util.TimeZone
import kotlin.test.assertEquals

class DateTimeHelperTest {
    @Test
    fun `Given an Instant, when asFormatted is called, expected format time is printed`() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")))
        val inputInstant = "2013-05-11T02:31:05Z".toInstant()
        val formattedDateOutput = inputInstant.asFormattedDate()
        assertEquals("11-05-2013 02:31", formattedDateOutput)
    }
}