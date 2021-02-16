// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.subscriptions.adapter

import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.ResponseField
import com.apollographql.apollo3.api.internal.IntResponseAdapter
import com.apollographql.apollo3.api.internal.NullableResponseAdapter
import com.apollographql.apollo3.api.internal.ResponseAdapter
import com.apollographql.apollo3.api.internal.StringResponseAdapter
import com.apollographql.apollo3.api.internal.json.JsonReader
import com.apollographql.apollo3.api.internal.json.JsonWriter
import com.example.subscriptions.TestSubscription
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestSubscription_ResponseAdapter(
  responseAdapterCache: ResponseAdapterCache
) : ResponseAdapter<TestSubscription.Data> {
  private val nullableCommentAddedAdapter: ResponseAdapter<TestSubscription.Data.CommentAdded?> =
      NullableResponseAdapter(CommentAdded(responseAdapterCache))

  override fun fromResponse(reader: JsonReader): TestSubscription.Data {
    var commentAdded: TestSubscription.Data.CommentAdded? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> commentAdded = nullableCommentAddedAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestSubscription.Data(
      commentAdded = commentAdded
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestSubscription.Data) {
    writer.beginObject()
    writer.name("commentAdded")
    nullableCommentAddedAdapter.toResponse(writer, value.commentAdded)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Comment"),
        fieldName = "commentAdded",
        arguments = mapOf<String, Any?>(
          "repoFullName" to mapOf<String, Any?>(
            "kind" to "Variable",
            "variableName" to "repo")),
        fieldSets = listOf(
          ResponseField.FieldSet(null, CommentAdded.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class CommentAdded(
    responseAdapterCache: ResponseAdapterCache
  ) : ResponseAdapter<TestSubscription.Data.CommentAdded> {
    private val intAdapter: ResponseAdapter<Int> = IntResponseAdapter

    private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

    override fun fromResponse(reader: JsonReader): TestSubscription.Data.CommentAdded {
      var id: Int? = null
      var content: String? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> id = intAdapter.fromResponse(reader)
          1 -> content = stringAdapter.fromResponse(reader)
          else -> break
        }
      }
      reader.endObject()
      return TestSubscription.Data.CommentAdded(
        id = id!!,
        content = content!!
      )
    }

    override fun toResponse(writer: JsonWriter, value: TestSubscription.Data.CommentAdded) {
      writer.beginObject()
      writer.name("id")
      intAdapter.toResponse(writer, value.id)
      writer.name("content")
      stringAdapter.toResponse(writer, value.content)
      writer.endObject()
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Int")),
          fieldName = "id",
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          fieldName = "content",
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }
  }
}
