package com.vnidens.kotlinx.collections

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class MapDelegateTest {

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
    fun custom_class_key_to_enum_map() {
        val s1 = MapDelegateTest.Sealed.S1()
        val s2 = MapDelegateTest.Sealed.S2()
        val s3 = MapDelegateTest.Sealed.S3()
        val s4 = MapDelegateTest.Sealed.S4()

        class A {
            val map = mapOf(
                    s1 to Enum.ENUM1,
                    s2 to Enum.ENUM2,
                    s3 to Enum.ENUM3
            )

            val first by MapDelegate(s1, map)
            val second by MapDelegate(s2, map)
            val third by MapDelegate(s3, map)

            val forth by MapDelegate(s4, map)

        }

        val a = A()

        assertEquals(Enum.ENUM1, a.first)
        assertEquals(Enum.ENUM2, a.second)
        assertEquals(Enum.ENUM3, a.third)

        assertEquals(a.map[s1], a.first)
        assertEquals(a.map[s2], a.second)
        assertEquals(a.map[s3], a.third)

        try {
            a.forth
            Assert.fail()
        } catch (e: Exception) {
            assertTrue(true)
        }

    }

    @Test
    fun enum_to_int_map() {

        class A {
            val map = mapOf(Enum.ENUM1 to 1, Enum.ENUM2 to 2, Enum.ENUM3 to 3)

            val first by MapDelegate(Enum.ENUM1, map)
            val second by MapDelegate(Enum.ENUM2, map)
            val third by MapDelegate(Enum.ENUM3, map)

            val forth by MapDelegate(Enum.ENUM4, map)
        }

        val a = A()

        assertEquals(1, a.first)
        assertEquals(2, a.second)
        assertEquals(3, a.third)

        assertEquals(a.map[Enum.ENUM1], a.first)
        assertEquals(a.map[Enum.ENUM2], a.second)
        assertEquals(a.map[Enum.ENUM3], a.third)

        try {
            a.forth
            Assert.fail()
        } catch (e: Exception) {
            assertTrue(true)
        }

    }

    @Test
    fun custom_class_key_to_any_map() {
        val s1 = MapDelegateTest.Sealed.S1()
        val s2 = MapDelegateTest.Sealed.S2()
        val s3 = MapDelegateTest.Sealed.S3()
        val s4 = MapDelegateTest.Sealed.S4()

        class A {
            val map = mapOf(
                    s1 to Enum.ENUM1,
                    s2 to "1",
                    s3 to 1
            )

            val first by MapDelegate(s1, map)
            val second by MapDelegate(s2, map)
            val third by MapDelegate(s3, map)

            val forth by MapDelegate(s4, map)

        }

        val a = A()

        assertEquals(Enum.ENUM1, a.first)
        assertEquals("1", a.second)
        assertNotEquals(1, a.second)
        assertEquals(1, a.third)

        try {
            a.forth
            Assert.fail()
        } catch (e: Exception) {
            assertTrue(true)
        }

    }

}