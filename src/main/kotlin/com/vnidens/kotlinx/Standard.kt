package com.vnidens.kotlinx

/**
 * Calls the specified [notNull] function block with `this` value
 * as its argument and returns `this` value if the received is not `null`.
 * Calls the specified or [ifNull] function block without arguments if `this` is `null`.
 */
inline fun <T> T?.also(notNull: (T) -> Unit, ifNull: () -> Unit): T? {
    if(this != null) notNull(this) else ifNull()
    return this
}

/**
 * Calls the specified [notNull] function block with `this` value
 * as its argument and returns its result if `this` is not `null`.
 * Calls the specified [ifNull] function block without arguments
 * and returns its result if `this` is `null`.
 */
inline fun <T, R> T?.let(notNull: (T) -> R, ifNull: () -> R): R {
    return if(this != null) notNull(this) else ifNull()
}
