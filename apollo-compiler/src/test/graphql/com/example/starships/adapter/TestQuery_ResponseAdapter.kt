// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.starships.adapter

import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.ResponseField
import com.apollographql.apollo3.api.internal.DoubleResponseAdapter
import com.apollographql.apollo3.api.internal.ListResponseAdapter
import com.apollographql.apollo3.api.internal.NullableResponseAdapter
import com.apollographql.apollo3.api.internal.ResponseAdapter
import com.apollographql.apollo3.api.internal.StringResponseAdapter
import com.apollographql.apollo3.api.internal.json.JsonReader
import com.apollographql.apollo3.api.internal.json.JsonWriter
import com.example.starships.TestQuery
import kotlin.Array
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery_ResponseAdapter(
  responseAdapterCache: ResponseAdapterCache
) : ResponseAdapter<TestQuery.Data> {
  private val nullableStarshipAdapter: ResponseAdapter<TestQuery.Data.Starship?> =
      NullableResponseAdapter(Starship(responseAdapterCache))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var starship: TestQuery.Data.Starship? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> starship = nullableStarshipAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      starship = starship
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    writer.beginObject()
    writer.name("starship")
    nullableStarshipAdapter.toResponse(writer, value.starship)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Starship"),
        fieldName = "starship",
        arguments = mapOf<String, Any?>(
          "id" to mapOf<String, Any?>(
            "kind" to "Variable",
            "variableName" to "id")),
        fieldSets = listOf(
          ResponseField.FieldSet(null, Starship.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Starship(
    responseAdapterCache: ResponseAdapterCache
  ) : ResponseAdapter<TestQuery.Data.Starship> {
    private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

    private val nullableListOfListOfFloatAdapter: ResponseAdapter<List<List<Double>>?> =
        NullableResponseAdapter(ListResponseAdapter(ListResponseAdapter(DoubleResponseAdapter)))

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Starship {
      var id: String? = null
      var name: String? = null
      var coordinates: List<List<Double>>? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> id = stringAdapter.fromResponse(reader)
          1 -> name = stringAdapter.fromResponse(reader)
          2 -> coordinates = nullableListOfListOfFloatAdapter.fromResponse(reader)
          else -> break
        }
      }
      reader.endObject()
      return TestQuery.Data.Starship(
        id = id!!,
        name = name!!,
        coordinates = coordinates
      )
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Starship) {
      writer.beginObject()
      writer.name("id")
      stringAdapter.toResponse(writer, value.id)
      writer.name("name")
      stringAdapter.toResponse(writer, value.name)
      writer.name("coordinates")
      nullableListOfListOfFloatAdapter.toResponse(writer, value.coordinates)
      writer.endObject()
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          fieldName = "id",
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          fieldName = "name",
        ),
        ResponseField(
          type =
              ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Float"))))),
          fieldName = "coordinates",
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }
  }
}
