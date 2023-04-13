import com.boundinteractive.kmmsample.data.KeyPantry
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class KeyPantryTest {
    private lateinit var testee: KeyPantry

    @BeforeTest
    fun init() {
        testee = KeyPantry().apply { clearAll() }
    }

    @Test
    fun test_string() {
        assertNull(testee.fetchString("someKey"))
        testee.saveString("someKey", value = "1234")
        assertEquals("1234", testee.fetchString("someKey"))
    }

    @Test
    fun test_Array() {
        assertNull(testee.fetchIntArray("someKey"))
        testee.saveIntArray("someKey", value = arrayOf(1,2,3,4))
        assertEquals(arrayOf(1,2,3,4), testee.fetchIntArray("someKey"))
    }
}