package com.vnidens.kotlinx.util

import java.util.*

var Calendar.year: Int
    get() = this[Calendar.YEAR]
    set(value) {
        this[Calendar.YEAR] = value
    }

operator fun Calendar.component1(): Int = year

var Calendar.month: Int
    get() = this[Calendar.MONTH]
    set(value) {
        this[Calendar.MONTH] = value
    }

operator fun Calendar.component2(): Int = month

var Calendar.day: Int
    get() = this[Calendar.DAY_OF_MONTH]
    set(value) {
        this[Calendar.DAY_OF_MONTH] = value
    }

operator fun Calendar.component3(): Int = day

var Calendar.hour: Int
    get() = this[Calendar.HOUR_OF_DAY]
    set(value) {
        this[Calendar.HOUR_OF_DAY] = value
    }

operator fun Calendar.component4(): Int = hour

var Calendar.minute: Int
    get() = this[Calendar.MINUTE]
    set(value) {
        this[Calendar.MINUTE] = value
    }

operator fun Calendar.component5(): Int = minute

var Calendar.second: Int
    get() = this[Calendar.SECOND]
    set(value) {
        this[Calendar.SECOND] = value
    }

operator fun Calendar.component6(): Int = second
