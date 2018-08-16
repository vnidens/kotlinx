package com.vnidens.kotlinx.delegate

import org.junit.Assert.*
import org.junit.Test

@Suppress("unused")
class RangeDelegateTest {

    @Test
    fun `range delegate with int range`() {
        class A {
            var prop: Int by RangeDelegate(0, 0..10)
        }

        val a = A()

        a.prop = 5
        assertEquals(5, a.prop)

        a.prop = 0
        assertEquals(0, a.prop)

        a.prop = 10
        assertEquals(10, a.prop)

        try {
            a.prop = -1
            fail("Value -1 should not be assigned: Not in range ${0..10}")
        } catch (e: Exception) { }

        try {
            a.prop = 11
            fail("Value 11 should not be assigned: Not in range ${0..10}")
        } catch (e: Exception) { }
    }

    @Test
    fun `range delegate with int range and wrong init`() {
        class A {
            var prop: Int by RangeDelegate(-1, 0..10)
        }

        try {
            A()
            fail()
        } catch (e: Exception) { }
    }

    @Test
    fun `range delegate with string range`() {
        val range = "a".."zz"
        class A {
            var prop: String by RangeDelegate("a", range)
        }

        val a = A()

        a.prop = "f"
        assertEquals("f", a.prop)

        a.prop = "a"
        assertEquals("a", a.prop)

        a.prop = "zz"
        assertEquals("zz", a.prop)

        try {
            a.prop = ""
            fail("Empty string should not be assigned: Not in range $range")
        } catch (e: Exception) { }

        try {
            a.prop = "zza"
            fail("String `zza` should not be assigned: Not in range $range")
        } catch (e: Exception) { }
    }

    @Test
    fun `range delegate with string range and wrong init`() {
        class A {
            var prop: String by RangeDelegate("zza", "a".."zz")
        }

        try {
            A()
            fail()
        } catch (e: Exception) { }
    }

    @Test
    fun `coercing range delegate with int range`() {
        class A {
            var prop: Int by CoercingRangeDelegate(0, 0..10)
        }

        val a = A()

        a.prop = 5
        assertEquals(5, a.prop)

        a.prop = 0
        assertEquals(0, a.prop)

        a.prop = 10
        assertEquals(10, a.prop)

        a.prop = -1
        assertEquals(0, a.prop)


        a.prop = 11
        assertEquals(10, a.prop)
    }

    @Test
    fun `coercing range delegate with int range and wrong init`() {
        class A {
            var prop: Int by CoercingRangeDelegate(-1, 0..10)
        }

        val a = A()
        assertEquals(0, a.prop)
    }

    @Test
    fun `coercing range delegate with string range`() {
        val range = "a".."zz"
        class A {
            var prop: String by CoercingRangeDelegate("a", range)
        }

        val a = A()

        a.prop = "f"
        assertEquals("f", a.prop)

        a.prop = "a"
        assertEquals("a", a.prop)

        a.prop = "zz"
        assertEquals("zz", a.prop)

        a.prop = ""
        assertEquals("a", a.prop)

        a.prop = "zza"
        assertEquals("zz", a.prop)
    }

    @Test
    fun `coercing range delegate with string range and wrong init`() {
        class A {
            var prop: String by CoercingRangeDelegate("zza", "a".."zz")
        }

        val a = A()
        assertEquals("zz", a.prop)
    }

}