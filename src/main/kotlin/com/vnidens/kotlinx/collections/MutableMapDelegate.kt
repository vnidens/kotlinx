package com.vnidens.kotlinx.collections

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * The delegate allows to use custom key with delegated mutable map for a class property
 *
 * @param key Key used for delegated value in the map
 * @param map Backing map instance
 **/
class MutableMapDelegate<K, V>(
        private val key: K,
        private val map: MutableMap<K, V>
) : ReadWriteProperty<Any, V> {
    override fun getValue(thisRef: Any, property: KProperty<*>): V = map[key]!!

    override fun setValue(thisRef: Any, property: KProperty<*>, value: V) {
        map[key] = value!!
    }

}
