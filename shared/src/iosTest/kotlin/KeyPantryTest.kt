import com.boundinteractive.kmmsample.data.KeyPantry
import kotlin.test.*

class KeyPantryTest {
    private lateinit var testee: KeyPantry

    private val key = "someKey"

    @BeforeTest
    fun init() {
        testee = KeyPantry().apply { clearAll() }
    }

    @Test
    fun saveString() {
        assertTrue(testee.saveString(key, value = "1234"))
    }

    @Test
    fun fetchString() {
        assertNull(testee.fetchString(key))

        testee.saveString(key, value = "1234")

        assertEquals("1234", testee.fetchString(key))
    }
}