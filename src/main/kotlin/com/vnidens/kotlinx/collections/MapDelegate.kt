package com.vnidens.kotlinx.collections

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * The delegate allows to use custom key with delegated immutable map for a class property
 *
 * @param key Key used for delegated value in the map
 * @param map Backing map instance
 */
class MapDelegate<K, V>(
        private val key: K,
        private val map: Map<K, V>
) : ReadOnlyProperty<Any, V> {
    override fun getValue(thisRef: Any, property: KProperty<*>): V = map[key]!!
}
