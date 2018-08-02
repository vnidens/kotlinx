package com.vnidens.kotlinx.collections

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class MutableMapDelegateTest {

    sealed class Sealed {
        class S1 : Sealed()

        class S2 : Sealed()

        class S3 : Sealed()

        class S4 : Sealed()
    }

    enum class Enum {
        ENUM1, ENUM2, ENUM3, ENUM4, ENUM5
    }

    @Test
    fun string_to_string_map() {
        class A {
            val map = mutableMapOf("1" to "2", "2" to "3", "3" to "4")

            var first by MutableMapDelegate("1", map)
            var second by MutableMapDelegate("2", map)
            val third by MutableMapDelegate("3", map)

            var forth by MutableMapDelegate("4", map)

        }

        val a = A()

        assertEquals("2", a.first)
        assertEquals("3", a.second)
        assertEquals("4", a.third)

        assertEquals(a.map["1"], a.first)
        assertEquals(a.map["2"], a.second)
        assertEquals(a.map["3"], a.third)

        a.first = "10"
        assertEquals("10", a.first)
        assertEquals("10", a.map["1"])

        a.map["2"] = "20"
        assertEquals("20", a.second)
        assertEquals("20", a.map["2"])

        try {
            a.forth
            Assert.fail()
        } catch (e: Exception) {
            assertTrue(true)
        }

        a.forth = "40"
        assertEquals("40", a.forth)
        assertEquals("40", a.map["4"])
    }

    @Test
    fun custom_class_key_map() {

        class A {
            val map = mutableMapOf(Enum.ENUM1 to 1, Enum.ENUM2 to 2, Enum.ENUM3 to 3)

            var first by MutableMapDelegate(Enum.ENUM1, map)
            var second by MutableMapDelegate(Enum.ENUM2, map)
            val third by MutableMapDelegate(Enum.ENUM3, map)

            var forth by MutableMapDelegate(Enum.ENUM4, map)
            val fifth by MutableMapDelegate(Enum.ENUM5, map)
        }

        val a = A()

        assertEquals(1, a.first)
        assertEquals(2, a.second)
        assertEquals(3, a.third)

        assertEquals(a.map[Enum.ENUM1], a.first)
        assertEquals(a.map[Enum.ENUM2], a.second)
        assertEquals(a.map[Enum.ENUM3], a.third)

        a.first = 10
        assertEquals(10, a.first)
        assertEquals(10, a.map[Enum.ENUM1])

        a.map[Enum.ENUM2] = 20
        assertEquals(20, a.second)
        assertEquals(20, a.map[Enum.ENUM2])

        try {
            a.forth
            Assert.fail()
        } catch (e: Exception) {
            assertTrue(true)
        }

        a.forth = 40
        assertEquals(40, a.forth)
        assertEquals(40, a.map[Enum.ENUM4])

        try {
            a.fifth
            Assert.fail()
        } catch (e: Exception) {
            assertTrue(true)
        }

        a.map[Enum.ENUM5] = 50
        assertEquals(50, a.fifth)
        assertEquals(50, a.map[Enum.ENUM5])
    }

    @Test
    fun custom_class_key_to_any_map() {
        val s1 = MutableMapDelegateTest.Sealed.S1()
        val s2 = MutableMapDelegateTest.Sealed.S2()
        val s3 = MutableMapDelegateTest.Sealed.S3()
        val s4 = MutableMapDelegateTest.Sealed.S4()

        class A {
            val map = mutableMapOf(
                    s1 to Enum.ENUM1,
                    s2 to "1",
                    s3 to 1
            )

            var first by MutableMapDelegate(s1, map)
            var second by MutableMapDelegate(s2, map)
            var third by MutableMapDelegate(s3, map)

            var forth by MutableMapDelegate(s4, map)

        }

        val a = A()

        assertEquals(Enum.ENUM1, a.first)
        assertEquals("1", a.second)
        assertNotEquals(1, a.second)
        assertEquals(1, a.third)

        assertEquals(a.map[s1], a.first)
        assertEquals(a.map[s2], a.second)
        assertEquals(a.map[s3], a.third)

        try {
            a.forth
            Assert.fail()
        } catch (e: Exception) {
            assertTrue(true)
        }

        a.map[s4] = s1
        assertEquals(s1, a.forth)
        assertEquals(a.map[s4], a.forth)

    }

}
