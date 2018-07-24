package com.vnidens.kotlinx

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class StandardTests {
    @Test
    fun test_nullable_let() {
        var obj: String? = null

        val res1 = obj.let(
                notNull = {
                    Assert.fail("`notNull` block shouldn't be executed with `null` object reference")
                    ""
                },
                ifNull = { "" }
        )

        assertEquals("", res1)

        obj = ""

        val res2 = obj.let(
                notNull = {
                    assertTrue(it.isEmpty())
                    it
                },
                ifNull = {
                    Assert.fail("`ifNull` block shouldn't be executed with non null object reference")
                }
        )

        assertEquals("", res2)
    }

    @Test
    fun test_nullable_also() {
        var obj: String? = null

        val ref1 = obj.also(
                notNull = { Assert.fail("`notNull` block shouldn't be executed with `null` object reference") },
                ifNull = { }
        )

        assertTrue(ref1 == null)

        obj = ""

        val ref2 = obj.also(
                notNull = { Assert.assertTrue(it.isEmpty()) },
                ifNull = { Assert.fail("`ifNull` block shouldn't be executed with non null object reference") }
        )

        assertTrue(ref2 != null)
        assertTrue(ref2?.isEmpty() ?: false)
    }
}
