// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.nested_conditional_inline.adapter

import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.ResponseField
import com.apollographql.apollo3.api.internal.DoubleResponseAdapter
import com.apollographql.apollo3.api.internal.ListResponseAdapter
import com.apollographql.apollo3.api.internal.NullableResponseAdapter
import com.apollographql.apollo3.api.internal.ResponseAdapter
import com.apollographql.apollo3.api.internal.StringResponseAdapter
import com.apollographql.apollo3.api.internal.json.JsonReader
import com.apollographql.apollo3.api.internal.json.JsonWriter
import com.example.nested_conditional_inline.TestQuery
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
  private val nullableHeroAdapter: ResponseAdapter<TestQuery.Data.Hero?> =
      NullableResponseAdapter(Hero(responseAdapterCache))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var hero: TestQuery.Data.Hero? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> hero = nullableHeroAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      hero = hero
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    writer.beginObject()
    writer.name("hero")
    nullableHeroAdapter.toResponse(writer, value.hero)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Character"),
        fieldName = "hero",
        arguments = mapOf<String, Any?>(
          "episode" to mapOf<String, Any?>(
            "kind" to "Variable",
            "variableName" to "episode")),
        fieldSets = listOf(
          ResponseField.FieldSet("Human", Hero.HumanHero.RESPONSE_FIELDS),
          ResponseField.FieldSet("Droid", Hero.DroidHero.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Hero.OtherHero.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Hero(
    responseAdapterCache: ResponseAdapterCache
  ) : ResponseAdapter<TestQuery.Data.Hero> {
    val HumanHeroAdapter: HumanHero =
        com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.HumanHero(responseAdapterCache)

    val DroidHeroAdapter: DroidHero =
        com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.DroidHero(responseAdapterCache)

    val OtherHeroAdapter: OtherHero =
        com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.OtherHero(responseAdapterCache)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Human" -> HumanHeroAdapter.fromResponse(reader, typename)
        "Droid" -> DroidHeroAdapter.fromResponse(reader, typename)
        else -> OtherHeroAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero) {
      when(value) {
        is TestQuery.Data.Hero.HumanHero -> HumanHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.DroidHero -> DroidHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.OtherHero -> OtherHeroAdapter.toResponse(writer, value)
      }
    }

    class HumanHero(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      private val nullableListOfNullableFriendsAdapter:
          ResponseAdapter<List<TestQuery.Data.Hero.HumanHero.Friends?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friends(responseAdapterCache))))

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.HumanHero {
        var __typename: String? = __typename
        var name: String? = null
        var friends: List<TestQuery.Data.Hero.HumanHero.Friends?>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            2 -> friends = nullableListOfNullableFriendsAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Hero.HumanHero(
          __typename = __typename!!,
          name = name!!,
          friends = friends
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.HumanHero) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        stringAdapter.toResponse(writer, value.name)
        writer.name("friends")
        nullableListOfNullableFriendsAdapter.toResponse(writer, value.friends)
        writer.endObject()
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.Typename,
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            fieldName = "name",
          ),
          ResponseField(
            type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
            fieldName = "friends",
            fieldSets = listOf(
              ResponseField.FieldSet("Human", Friends.HumanFriends.RESPONSE_FIELDS),
              ResponseField.FieldSet(null, Friends.OtherFriends.RESPONSE_FIELDS),
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Friends(
        responseAdapterCache: ResponseAdapterCache
      ) : ResponseAdapter<TestQuery.Data.Hero.HumanHero.Friends> {
        val HumanFriendsAdapter: HumanFriends =
            com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.HumanHero.Friends.HumanFriends(responseAdapterCache)

        val OtherFriendsAdapter: OtherFriends =
            com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.HumanHero.Friends.OtherFriends(responseAdapterCache)

        override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero.HumanHero.Friends {
          reader.beginObject()
          check(reader.nextName() == "__typename")
          val typename = reader.nextString()

          return when(typename) {
            "Human" -> HumanFriendsAdapter.fromResponse(reader, typename)
            else -> OtherFriendsAdapter.fromResponse(reader, typename)
          }
          .also { reader.endObject() }
        }

        override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.HumanHero.Friends) {
          when(value) {
            is TestQuery.Data.Hero.HumanHero.Friends.HumanFriends -> HumanFriendsAdapter.toResponse(writer, value)
            is TestQuery.Data.Hero.HumanHero.Friends.OtherFriends -> OtherFriendsAdapter.toResponse(writer, value)
          }
        }

        class HumanFriends(
          responseAdapterCache: ResponseAdapterCache
        ) {
          private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

          private val nullableFloatAdapter: ResponseAdapter<Double?> =
              NullableResponseAdapter(DoubleResponseAdapter)

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Hero.HumanHero.Friends.HumanFriends {
            var __typename: String? = __typename
            var name: String? = null
            var height: Double? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = stringAdapter.fromResponse(reader)
                1 -> name = stringAdapter.fromResponse(reader)
                2 -> height = nullableFloatAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Hero.HumanHero.Friends.HumanFriends(
              __typename = __typename!!,
              name = name!!,
              height = height
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Hero.HumanHero.Friends.HumanFriends) {
            writer.beginObject()
            writer.name("__typename")
            stringAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            stringAdapter.toResponse(writer, value.name)
            writer.name("height")
            nullableFloatAdapter.toResponse(writer, value.height)
            writer.endObject()
          }

          companion object {
            val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField.Typename,
              ResponseField(
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                fieldName = "name",
              ),
              ResponseField(
                type = ResponseField.Type.Named.Other("Float"),
                fieldName = "height",
                arguments = mapOf<String, Any?>(
                  "unit" to "FOOT"),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }
        }

        class OtherFriends(
          responseAdapterCache: ResponseAdapterCache
        ) {
          private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Hero.HumanHero.Friends.OtherFriends {
            var __typename: String? = __typename
            var name: String? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = stringAdapter.fromResponse(reader)
                1 -> name = stringAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Hero.HumanHero.Friends.OtherFriends(
              __typename = __typename!!,
              name = name!!
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Hero.HumanHero.Friends.OtherFriends) {
            writer.beginObject()
            writer.name("__typename")
            stringAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            stringAdapter.toResponse(writer, value.name)
            writer.endObject()
          }

          companion object {
            val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField.Typename,
              ResponseField(
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                fieldName = "name",
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }
        }
      }
    }

    class DroidHero(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      private val nullableListOfNullableFriendsAdapter:
          ResponseAdapter<List<TestQuery.Data.Hero.DroidHero.Friends?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friends(responseAdapterCache))))

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.DroidHero {
        var __typename: String? = __typename
        var name: String? = null
        var friends: List<TestQuery.Data.Hero.DroidHero.Friends?>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            2 -> friends = nullableListOfNullableFriendsAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Hero.DroidHero(
          __typename = __typename!!,
          name = name!!,
          friends = friends
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.DroidHero) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        stringAdapter.toResponse(writer, value.name)
        writer.name("friends")
        nullableListOfNullableFriendsAdapter.toResponse(writer, value.friends)
        writer.endObject()
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.Typename,
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            fieldName = "name",
          ),
          ResponseField(
            type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
            fieldName = "friends",
            fieldSets = listOf(
              ResponseField.FieldSet("Human", Friends.HumanFriends.RESPONSE_FIELDS),
              ResponseField.FieldSet(null, Friends.OtherFriends.RESPONSE_FIELDS),
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Friends(
        responseAdapterCache: ResponseAdapterCache
      ) : ResponseAdapter<TestQuery.Data.Hero.DroidHero.Friends> {
        val HumanFriendsAdapter: HumanFriends =
            com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.DroidHero.Friends.HumanFriends(responseAdapterCache)

        val OtherFriendsAdapter: OtherFriends =
            com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.DroidHero.Friends.OtherFriends(responseAdapterCache)

        override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero.DroidHero.Friends {
          reader.beginObject()
          check(reader.nextName() == "__typename")
          val typename = reader.nextString()

          return when(typename) {
            "Human" -> HumanFriendsAdapter.fromResponse(reader, typename)
            else -> OtherFriendsAdapter.fromResponse(reader, typename)
          }
          .also { reader.endObject() }
        }

        override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.DroidHero.Friends) {
          when(value) {
            is TestQuery.Data.Hero.DroidHero.Friends.HumanFriends -> HumanFriendsAdapter.toResponse(writer, value)
            is TestQuery.Data.Hero.DroidHero.Friends.OtherFriends -> OtherFriendsAdapter.toResponse(writer, value)
          }
        }

        class HumanFriends(
          responseAdapterCache: ResponseAdapterCache
        ) {
          private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

          private val nullableFloatAdapter: ResponseAdapter<Double?> =
              NullableResponseAdapter(DoubleResponseAdapter)

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Hero.DroidHero.Friends.HumanFriends {
            var __typename: String? = __typename
            var name: String? = null
            var height: Double? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = stringAdapter.fromResponse(reader)
                1 -> name = stringAdapter.fromResponse(reader)
                2 -> height = nullableFloatAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Hero.DroidHero.Friends.HumanFriends(
              __typename = __typename!!,
              name = name!!,
              height = height
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Hero.DroidHero.Friends.HumanFriends) {
            writer.beginObject()
            writer.name("__typename")
            stringAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            stringAdapter.toResponse(writer, value.name)
            writer.name("height")
            nullableFloatAdapter.toResponse(writer, value.height)
            writer.endObject()
          }

          companion object {
            val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField.Typename,
              ResponseField(
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                fieldName = "name",
              ),
              ResponseField(
                type = ResponseField.Type.Named.Other("Float"),
                fieldName = "height",
                arguments = mapOf<String, Any?>(
                  "unit" to "METER"),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }
        }

        class OtherFriends(
          responseAdapterCache: ResponseAdapterCache
        ) {
          private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Hero.DroidHero.Friends.OtherFriends {
            var __typename: String? = __typename
            var name: String? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = stringAdapter.fromResponse(reader)
                1 -> name = stringAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Hero.DroidHero.Friends.OtherFriends(
              __typename = __typename!!,
              name = name!!
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Hero.DroidHero.Friends.OtherFriends) {
            writer.beginObject()
            writer.name("__typename")
            stringAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            stringAdapter.toResponse(writer, value.name)
            writer.endObject()
          }

          companion object {
            val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField.Typename,
              ResponseField(
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                fieldName = "name",
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }
        }
      }
    }

    class OtherHero(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.OtherHero {
        var __typename: String? = __typename
        var name: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Hero.OtherHero(
          __typename = __typename!!,
          name = name!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.OtherHero) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        stringAdapter.toResponse(writer, value.name)
        writer.endObject()
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.Typename,
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            fieldName = "name",
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }
  }
}
