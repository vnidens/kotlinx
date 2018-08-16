package com.vnidens.kotlinx.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class AbstractRangeDelegate<T: Comparable<T>> : ReadWriteProperty<Any?, T> {

    protected abstract var value: T

    protected abstract fun validateValue(value: T): T

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = validateValue(value)
    }
}

class RangeDelegate<T: Comparable<T>>(
        value: T,
        private val range: ClosedRange<T>
) : AbstractRangeDelegate<T>() {

    override var value: T = validateValue(value)

    override fun validateValue(value: T): T {
        require(value in range) {
            "Value must be in range $range. Given value: $value"
        }

        return value
    }
}

class CoercingRangeDelegate<T: Comparable<T>>(
        value: T,
        private val range: ClosedRange<T>
) : AbstractRangeDelegate<T>() {

    override var value: T = validateValue(value)

    override fun validateValue(value: T): T = value.coerceIn(range)

}
