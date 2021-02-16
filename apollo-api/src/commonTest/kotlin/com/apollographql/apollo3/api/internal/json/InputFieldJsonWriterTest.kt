package com.apollographql.apollo3.api.internal.json

import com.apollographql.apollo3.api.BigDecimal
import com.apollographql.apollo3.api.CustomScalarAdapter
import com.apollographql.apollo3.api.JsonElement
import com.apollographql.apollo3.api.CustomScalar
import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.JsonBoolean
import com.apollographql.apollo3.api.JsonNull
import com.apollographql.apollo3.api.JsonNumber
import com.apollographql.apollo3.api.JsonString
import com.apollographql.apollo3.api.internal.InputFieldMarshaller
import com.apollographql.apollo3.api.internal.InputFieldWriter
import com.apollographql.apollo3.api.toNumber
import okio.Buffer
import kotlin.test.Test
import kotlin.test.assertEquals

class InputFieldJsonWriterTest {
  private val jsonBuffer = Buffer()
  private val jsonWriter = JsonWriter.of(jsonBuffer).apply {
    serializeNulls = true
    beginObject()
  }
  private val inputFieldJsonWriter = InputFieldJsonWriter(jsonWriter, ResponseAdapterCache(emptyMap()))

  @Test
  fun writeString() {
    inputFieldJsonWriter.writeString("someField", "someValue")
    inputFieldJsonWriter.writeString("someNullField", null)
    assertEquals("{\"someField\":\"someValue\",\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeInt() {
    inputFieldJsonWriter.writeInt("someField", 1)
    inputFieldJsonWriter.writeInt("someNullField", null)
    assertEquals("{\"someField\":1,\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeLong() {
    inputFieldJsonWriter.writeLong("someField", 10L)
    inputFieldJsonWriter.writeLong("someNullField", null)
    assertEquals("{\"someField\":10,\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeDouble() {
    inputFieldJsonWriter.writeDouble("someField", 1.01)
    inputFieldJsonWriter.writeDouble("someNullField", null)
    assertEquals("{\"someField\":1.01,\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeNumber() {
    inputFieldJsonWriter.writeNumber("someField", BigDecimal("1.001").toNumber())
    inputFieldJsonWriter.writeNumber("someNullField", null)
    assertEquals("{\"someField\":1.001,\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeBoolean() {
    inputFieldJsonWriter.writeBoolean("someField", true)
    inputFieldJsonWriter.writeBoolean("someNullField", null)
    assertEquals("{\"someField\":true,\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeObject() {
    inputFieldJsonWriter.writeObject("someField", object : InputFieldMarshaller {
      override fun marshal(writer: InputFieldWriter) {
        writer.writeString("someField", "someValue")
      }
    })
    inputFieldJsonWriter.writeObject("someNullField", null)
    assertEquals("{\"someField\":{\"someField\":\"someValue\"},\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeList() {
    inputFieldJsonWriter.writeList("someField", object : InputFieldWriter.ListWriter {
      override fun write(listItemWriter: InputFieldWriter.ListItemWriter) {
        listItemWriter.writeString("someValue")
      }
    })
    inputFieldJsonWriter.writeList("someNullField", null)
    assertEquals("{\"someField\":[\"someValue\"],\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeCustomBoolean() {
    val customScalarAdapters: MutableMap<CustomScalar, CustomScalarAdapter<*>> = HashMap()
    val customScalar = CustomScalar(JsonBoolean::class.simpleName!!, "com.apollographql.apollo3.api.JsonElement.GraphQLBoolean")
    customScalarAdapters[customScalar] = object : MockCustomScalarAdapter() {
      override fun encode(value: Any?): JsonElement {
        return JsonBoolean((value as Boolean))
      }
    }
    val inputFieldJsonWriter = InputFieldJsonWriter(jsonWriter, ResponseAdapterCache(customScalarAdapters))
    inputFieldJsonWriter.writeCustom("someField", customScalar, true)
    inputFieldJsonWriter.writeCustom("someNullField", customScalar, null)
    assertEquals("{\"someField\":true,\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeCustomNumber() {
    val customScalarAdapters: MutableMap<CustomScalar, CustomScalarAdapter<*>> = HashMap()
    val customScalar = CustomScalar(JsonNumber::class.simpleName!!, "com.apollographql.apollo3.api.JsonElement.GraphQLNumber")
    customScalarAdapters[customScalar] = object : MockCustomScalarAdapter() {
      override fun encode(value: Any?): JsonElement {
        return JsonNumber((value as BigDecimal).toNumber())
      }
    }
    val inputFieldJsonWriter = InputFieldJsonWriter(jsonWriter, ResponseAdapterCache(customScalarAdapters))
    inputFieldJsonWriter.writeCustom("someField", customScalar, BigDecimal("100.1"))
    inputFieldJsonWriter.writeCustom("someNullField", customScalar, null)
    assertEquals("{\"someField\":100.1,\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeCustomString() {
    val customScalarAdapters: MutableMap<CustomScalar, CustomScalarAdapter<*>> = HashMap()
    val customScalar = CustomScalar(JsonString::class.simpleName!!, "com.apollographql.apollo3.api.JsonElement.JsonString")
    customScalarAdapters[customScalar] = object : MockCustomScalarAdapter() {
      override fun encode(value: Any?): JsonElement {
        return JsonString((value as String))
      }
    }
    val inputFieldJsonWriter = InputFieldJsonWriter(jsonWriter, ResponseAdapterCache(customScalarAdapters))
    inputFieldJsonWriter.writeCustom("someField", customScalar, "someValue")
    inputFieldJsonWriter.writeCustom("someNullField", customScalar, null)
    assertEquals("{\"someField\":\"someValue\",\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeCustomNull() {
    val customScalarAdapters: MutableMap<CustomScalar, CustomScalarAdapter<*>> = HashMap()
    val customScalar = CustomScalar(JsonNumber::class.simpleName!!, "com.apollographql.apollo3.api.JsonElement.JsonNumber")
    customScalarAdapters[customScalar] = object : MockCustomScalarAdapter() {
      override fun encode(value: Any?): JsonElement {
        return JsonNull
      }
    }
    val inputFieldJsonWriter = InputFieldJsonWriter(jsonWriter, ResponseAdapterCache(customScalarAdapters))
    inputFieldJsonWriter.writeCustom("someField", customScalar, null)
    assertEquals("{\"someField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeCustomJsonObject() {
    val value = mapOf(
        "stringField" to "string",
        "booleanField" to true,
        "numberField" to 100,
        "listField" to listOf(
            "string",
            true,
            100,
            mapOf(
                "stringField" to "string",
                "numberField" to 100,
                "booleanField" to true,
                "listField" to listOf(1, 2, 3)
            )
        ),
        "objectField" to mapOf(
            "stringField" to "string",
            "numberField" to 100,
            "booleanField" to true,
            "listField" to listOf(1, 2, 3)
        )
    )
    val customScalar = CustomScalar(Map::class.simpleName!!, "kotlin.collections.Map")
    inputFieldJsonWriter.writeCustom("someField", customScalar, value)
    inputFieldJsonWriter.writeCustom("someNullField", customScalar, null)
    assertEquals("{\"someField\":{\"stringField\":\"string\",\"booleanField\":true,\"numberField\":100,\"listField\":[\"string\",true,100,{\"stringField\":\"string\",\"numberField\":100,\"booleanField\":true,\"listField\":[1,2,3]}],\"objectField\":{\"stringField\":\"string\",\"numberField\":100,\"booleanField\":true,\"listField\":[1,2,3]}},\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeCustomList() {
    val value = listOf(
        "string",
        true,
        100,
        mapOf(
            "stringField" to "string",
            "numberField" to 100,
            "booleanField" to true,
            "listField" to listOf(1, 2, 3)
        )
    )
    val customScalar = CustomScalar(List::class.simpleName!!, "kotlin.collections.List")
    inputFieldJsonWriter.writeCustom("someField", customScalar, value)
    inputFieldJsonWriter.writeCustom("someNullField", customScalar, null)
    assertEquals("{\"someField\":[\"string\",true,100,{\"stringField\":\"string\",\"numberField\":100,\"booleanField\":true,\"listField\":[1,2,3]}],\"someNullField\":null", jsonBuffer.readUtf8())
  }

  @Test
  fun writeListOfList() {
    inputFieldJsonWriter.writeList("someField", object : InputFieldWriter.ListWriter {
      override fun write(listItemWriter: InputFieldWriter.ListItemWriter) {
        listItemWriter.writeList(object : InputFieldWriter.ListWriter {
          override fun write(listItemWriter: InputFieldWriter.ListItemWriter) {
            listItemWriter.writeString("someValue")
          }
        })
      }
    })
    inputFieldJsonWriter.writeList("someNullField", null)
    assertEquals("{\"someField\":[[\"someValue\"]],\"someNullField\":null", jsonBuffer.readUtf8())
  }

  private abstract inner class MockCustomScalarAdapter : CustomScalarAdapter<Any?> {
    override fun decode(jsonElement: JsonElement): Any {
      throw UnsupportedOperationException()
    }
  }
}
