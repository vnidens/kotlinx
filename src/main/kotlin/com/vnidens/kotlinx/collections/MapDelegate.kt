package com.vnidens.kotlinx.collections

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MapDelegate<K, V>(
        private val key: K,
        private val map: Map<K, V>
) : ReadOnlyProperty<Any, V> {
    override fun getValue(thisRef: Any, property: KProperty<*>): V = map[key]!!
}
