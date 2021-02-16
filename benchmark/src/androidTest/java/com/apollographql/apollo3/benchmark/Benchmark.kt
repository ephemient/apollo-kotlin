package com.apollographql.apollo3.benchmark

import Utils.bufferedSource
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.platform.app.InstrumentationRegistry
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.parse
import com.apollographql.apollo3.benchmark.moshi.Query
import com.apollographql.apollo3.cache.CacheHeaders
import com.apollographql.apollo3.cache.normalized.CacheKeyResolver
import com.apollographql.apollo3.cache.normalized.MemoryCache
import com.apollographql.apollo3.cache.normalized.Record
import com.apollographql.apollo3.cache.normalized.internal.ReadMode
import com.apollographql.apollo3.cache.normalized.internal.ReadableStore
import com.apollographql.apollo3.cache.normalized.internal.normalize
import com.apollographql.apollo3.cache.normalized.internal.readDataFromCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.squareup.moshi.Moshi
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test

class Benchmark {
  @get:Rule
  val benchmarkRule = BenchmarkRule()


  @Test
  fun moshi() = benchmarkRule.measureRepeated {
    val bufferedSource = runWithTimingDisabled {
      bufferedSource()
    }

    moshiAdapter.fromJson(bufferedSource)
  }

  @Test
  fun apollo() = benchmarkRule.measureRepeated {
    val bufferedSource = runWithTimingDisabled {
      bufferedSource()
    }

    operation.parse(bufferedSource, customScalarAdapters)
  }

  @Test
  fun apolloParseAndNormalize() = benchmarkRule.measureRepeated {
    val bufferedSource = runWithTimingDisabled {
      bufferedSource()
    }

    val data = operation.parse(bufferedSource, customScalarAdapters).data!!
    val records = operation.normalize(data, CustomScalarAdapters.DEFAULT, CacheKeyResolver.DEFAULT)
  }

  companion object {
    lateinit var sqlReadableStore: ReadableStore
    lateinit var memoryReadableStore: ReadableStore
    private val operation = GetResponseQuery()
    private val moshiAdapter = Moshi.Builder().build().adapter(Query::class.java)
    private val customScalarAdapters = CustomScalarAdapters(emptyMap())

    @BeforeClass
    @JvmStatic
    fun setup() {
      val data = operation.parse(bufferedSource()).data!!
      val records = operation.normalize(data, CustomScalarAdapters.DEFAULT, CacheKeyResolver.DEFAULT).values

      // warm the adapter
      operation.adapter(customScalarAdapters)

      val sqlCache = SqlNormalizedCacheFactory(context = InstrumentationRegistry.getInstrumentation().context).create()
      sqlCache.merge(records, CacheHeaders.NONE)
      sqlReadableStore = object : ReadableStore {
        override fun read(key: String, cacheHeaders: CacheHeaders): Record? {
          return sqlCache.loadRecord(key, cacheHeaders)
        }

        override fun read(keys: Collection<String>, cacheHeaders: CacheHeaders): Collection<Record> {
          return sqlCache.loadRecords(keys, cacheHeaders)
        }
      }

      val memoryCache = MemoryCache(Int.MAX_VALUE)
      memoryCache.merge(records, CacheHeaders.NONE)
      memoryReadableStore = object : ReadableStore {
        override fun read(key: String, cacheHeaders: CacheHeaders): Record? {
          return memoryCache.loadRecord(key, cacheHeaders)
        }

        override fun read(keys: Collection<String>, cacheHeaders: CacheHeaders): Collection<Record> {
          return memoryCache.loadRecords(keys, cacheHeaders)
        }
      }
    }
  }


  @Test
  fun apolloReadCacheSql() = benchmarkRule.measureRepeated {
    val data2 = operation.readDataFromCache(CustomScalarAdapters.DEFAULT, sqlReadableStore, CacheKeyResolver.DEFAULT, CacheHeaders.NONE)
  }

  @Test
  fun apolloBatchCacheSql() = benchmarkRule.measureRepeated {
    val data2 = operation.readDataFromCache(CustomScalarAdapters.DEFAULT, sqlReadableStore, CacheKeyResolver.DEFAULT, CacheHeaders.NONE, ReadMode.BATCH)
  }

  @Test
  fun apolloReadCacheMemory() = benchmarkRule.measureRepeated {
    val data2 = operation.readDataFromCache(CustomScalarAdapters.DEFAULT, memoryReadableStore, CacheKeyResolver.DEFAULT, CacheHeaders.NONE)
  }

  @Test
  fun apolloBatchCacheMemory() = benchmarkRule.measureRepeated {
    val data2 = operation.readDataFromCache(CustomScalarAdapters.DEFAULT, memoryReadableStore, CacheKeyResolver.DEFAULT, CacheHeaders.NONE, ReadMode.BATCH)
  }
}