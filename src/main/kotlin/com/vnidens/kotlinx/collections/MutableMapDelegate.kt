package com.vnidens.kotlinx.collections

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MutableMapDelegate<K, V>(
        private val key: K,
        private val map: MutableMap<K, V>
) : ReadWriteProperty<Any, V> {
    override fun getValue(thisRef: Any, property: KProperty<*>): V = map[key]!!

    override fun setValue(thisRef: Any, property: KProperty<*>, value: V) {
        map[key] = value!!
    }

}
