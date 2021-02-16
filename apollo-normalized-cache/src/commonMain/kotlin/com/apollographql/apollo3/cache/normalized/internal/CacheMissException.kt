@file:Suppress("MemberVisibilityCanBePrivate")

package com.apollographql.apollo3.cache.normalized.internal

import com.apollographql.apollo3.cache.normalized.Record

class CacheMissException(
    val record: Record,
    val fieldName: String
) : IllegalStateException() {

  override val message: String
    get() = "Missing value: $fieldName for $record"
}
