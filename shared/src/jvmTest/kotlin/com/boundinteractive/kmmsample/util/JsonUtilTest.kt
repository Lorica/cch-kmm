package com.boundinteractive.kmmsample.util

import com.boundinteractive.kmmsample.util.JsonUtil.decodeFromString
import com.boundinteractive.kmmsample.util.JsonUtil.toJson
import kotlinx.serialization.Serializable
import org.junit.Test
import kotlin.test.assertEquals

class JsonUtilTest {
    @Test
    fun `when toJson then encode to json`() {
        val array = arrayListOf(1, 2, 3)
        val result = array.toJson()
        assertEquals("[1,2,3]", result)
    }

    @Test
    fun `when toJson and type has is serialisable then encode to json`() {
        @Serializable data class Person(val name: String, val age: Int)

        val result = Person(name = "Bob", age = 21).toJson()
        assertEquals("{\"name\":\"Bob\",\"age\":21}", result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `when toJson and type has no serializer then throw exception`() {
        data class Person(val name: String)

        Person(name = "Bob").toJson()
    }

    @Test
    fun `when decodeFromString then encode to type`() {
        val json = "[1,2,3]"
        assertEquals(arrayListOf(1,2,3), json.decodeFromString())
    }

    @Test
    fun `when decodeFromString and encoding type is serialisable then encode to type`() {
        @Serializable data class Person(val name: String, val age: Int)

        val json = "{\"name\":\"Bob\",\"age\":21}"
        json.decodeFromString<Person>().run {
            assertEquals("Bob", name)
            assertEquals(21, age)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `when decodeFromString and encoding type is not serialisable then throw exception`() {
        data class Person(val name: String)

        val json = "{\"name\":\"Bob\"}"
        json.decodeFromString<Person>().run {
            assertEquals("Bob", name)
        }
    }
}