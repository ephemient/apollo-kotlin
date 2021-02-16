// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.union_inline_fragments.adapter

import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.ResponseField
import com.apollographql.apollo3.api.internal.ListResponseAdapter
import com.apollographql.apollo3.api.internal.NullableResponseAdapter
import com.apollographql.apollo3.api.internal.ResponseAdapter
import com.apollographql.apollo3.api.internal.StringResponseAdapter
import com.apollographql.apollo3.api.internal.json.JsonReader
import com.apollographql.apollo3.api.internal.json.JsonWriter
import com.example.union_inline_fragments.TestQuery
import com.example.union_inline_fragments.type.Episode
import com.example.union_inline_fragments.type.Episode_ResponseAdapter
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery_ResponseAdapter(
  responseAdapterCache: ResponseAdapterCache
) : ResponseAdapter<TestQuery.Data> {
  private val nullableListOfNullableSearchAdapter: ResponseAdapter<List<TestQuery.Data.Search?>?> =
      NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Search(responseAdapterCache))))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var search: List<TestQuery.Data.Search?>? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> search = nullableListOfNullableSearchAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      search = search
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    writer.beginObject()
    writer.name("search")
    nullableListOfNullableSearchAdapter.toResponse(writer, value.search)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.List(ResponseField.Type.Named.Object("SearchResult")),
        fieldName = "search",
        arguments = mapOf<String, Any?>(
          "text" to "test"),
        fieldSets = listOf(
          ResponseField.FieldSet("Human", Search.CharacterSearch.RESPONSE_FIELDS),
          ResponseField.FieldSet("Droid", Search.CharacterSearch.RESPONSE_FIELDS),
          ResponseField.FieldSet("Starship", Search.StarshipSearch.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Search.OtherSearch.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Search(
    responseAdapterCache: ResponseAdapterCache
  ) : ResponseAdapter<TestQuery.Data.Search> {
    val CharacterSearchAdapter: CharacterSearch =
        com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch(responseAdapterCache)

    val StarshipSearchAdapter: StarshipSearch =
        com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.StarshipSearch(responseAdapterCache)

    val OtherSearchAdapter: OtherSearch =
        com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.OtherSearch(responseAdapterCache)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Search {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Human" -> CharacterSearchAdapter.fromResponse(reader, typename)
        "Droid" -> CharacterSearchAdapter.fromResponse(reader, typename)
        "Starship" -> StarshipSearchAdapter.fromResponse(reader, typename)
        else -> OtherSearchAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Search) {
      when(value) {
        is TestQuery.Data.Search.CharacterSearch -> CharacterSearchAdapter.toResponse(writer, value)
        is TestQuery.Data.Search.StarshipSearch -> StarshipSearchAdapter.toResponse(writer, value)
        is TestQuery.Data.Search.OtherSearch -> OtherSearchAdapter.toResponse(writer, value)
      }
    }

    class CharacterSearch(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      private val nullableListOfNullableFriendsAdapter:
          ResponseAdapter<List<TestQuery.Data.Search.CharacterSearch.Friends?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friends(responseAdapterCache))))

      fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Search.CharacterSearch {
        var __typename: String? = __typename
        var id: String? = null
        var name: String? = null
        var friends: List<TestQuery.Data.Search.CharacterSearch.Friends?>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> id = stringAdapter.fromResponse(reader)
            2 -> name = stringAdapter.fromResponse(reader)
            3 -> friends = nullableListOfNullableFriendsAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Search.CharacterSearch(
          __typename = __typename!!,
          id = id!!,
          name = name!!,
          friends = friends
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Search.CharacterSearch) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("id")
        stringAdapter.toResponse(writer, value.id)
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
            fieldName = "id",
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            fieldName = "name",
          ),
          ResponseField(
            type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
            fieldName = "friends",
            fieldSets = listOf(
              ResponseField.FieldSet("Droid", Friends.CharacterDroidFriends.RESPONSE_FIELDS),
              ResponseField.FieldSet("Human", Friends.CharacterHumanFriends.RESPONSE_FIELDS),
              ResponseField.FieldSet(null, Friends.OtherFriends.RESPONSE_FIELDS),
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Friends(
        responseAdapterCache: ResponseAdapterCache
      ) : ResponseAdapter<TestQuery.Data.Search.CharacterSearch.Friends> {
        val CharacterDroidFriendsAdapter: CharacterDroidFriends =
            com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friends.CharacterDroidFriends(responseAdapterCache)

        val CharacterHumanFriendsAdapter: CharacterHumanFriends =
            com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friends.CharacterHumanFriends(responseAdapterCache)

        val OtherFriendsAdapter: OtherFriends =
            com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friends.OtherFriends(responseAdapterCache)

        override fun fromResponse(reader: JsonReader):
            TestQuery.Data.Search.CharacterSearch.Friends {
          reader.beginObject()
          check(reader.nextName() == "__typename")
          val typename = reader.nextString()

          return when(typename) {
            "Droid" -> CharacterDroidFriendsAdapter.fromResponse(reader, typename)
            "Human" -> CharacterHumanFriendsAdapter.fromResponse(reader, typename)
            else -> OtherFriendsAdapter.fromResponse(reader, typename)
          }
          .also { reader.endObject() }
        }

        override fun toResponse(writer: JsonWriter,
            value: TestQuery.Data.Search.CharacterSearch.Friends) {
          when(value) {
            is TestQuery.Data.Search.CharacterSearch.Friends.CharacterDroidFriends -> CharacterDroidFriendsAdapter.toResponse(writer, value)
            is TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends -> CharacterHumanFriendsAdapter.toResponse(writer, value)
            is TestQuery.Data.Search.CharacterSearch.Friends.OtherFriends -> OtherFriendsAdapter.toResponse(writer, value)
          }
        }

        class CharacterDroidFriends(
          responseAdapterCache: ResponseAdapterCache
        ) {
          private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

          private val nullableStringAdapter: ResponseAdapter<String?> =
              NullableResponseAdapter(StringResponseAdapter)

          private val nullableListOfNullableFriendsAdapter:
              ResponseAdapter<List<TestQuery.Data.Search.CharacterSearch.Friends.CharacterDroidFriends.Friends?>?>
              =
              NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friends(responseAdapterCache))))

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Search.CharacterSearch.Friends.CharacterDroidFriends {
            var __typename: String? = __typename
            var name: String? = null
            var primaryFunction: String? = null
            var friends: List<TestQuery.Data.Search.CharacterSearch.Friends.CharacterDroidFriends.Friends?>? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = stringAdapter.fromResponse(reader)
                1 -> name = stringAdapter.fromResponse(reader)
                2 -> primaryFunction = nullableStringAdapter.fromResponse(reader)
                3 -> friends = nullableListOfNullableFriendsAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Search.CharacterSearch.Friends.CharacterDroidFriends(
              __typename = __typename!!,
              name = name!!,
              primaryFunction = primaryFunction,
              friends = friends
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Search.CharacterSearch.Friends.CharacterDroidFriends) {
            writer.beginObject()
            writer.name("__typename")
            stringAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            stringAdapter.toResponse(writer, value.name)
            writer.name("primaryFunction")
            nullableStringAdapter.toResponse(writer, value.primaryFunction)
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
                type = ResponseField.Type.Named.Other("String"),
                fieldName = "primaryFunction",
              ),
              ResponseField(
                type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
                fieldName = "friends",
                fieldSets = listOf(
                  ResponseField.FieldSet(null, Friends.RESPONSE_FIELDS)
                ),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }

          class Friends(
            responseAdapterCache: ResponseAdapterCache
          ) :
              ResponseAdapter<TestQuery.Data.Search.CharacterSearch.Friends.CharacterDroidFriends.Friends>
              {
            private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

            override fun fromResponse(reader: JsonReader):
                TestQuery.Data.Search.CharacterSearch.Friends.CharacterDroidFriends.Friends {
              var id: String? = null
              reader.beginObject()
              while(true) {
                when (reader.selectName(RESPONSE_NAMES)) {
                  0 -> id = stringAdapter.fromResponse(reader)
                  else -> break
                }
              }
              reader.endObject()
              return TestQuery.Data.Search.CharacterSearch.Friends.CharacterDroidFriends.Friends(
                id = id!!
              )
            }

            override fun toResponse(writer: JsonWriter,
                value: TestQuery.Data.Search.CharacterSearch.Friends.CharacterDroidFriends.Friends) {
              writer.beginObject()
              writer.name("id")
              stringAdapter.toResponse(writer, value.id)
              writer.endObject()
            }

            companion object {
              val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                ResponseField(
                  type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                  fieldName = "id",
                )
              )

              val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
            }
          }
        }

        class CharacterHumanFriends(
          responseAdapterCache: ResponseAdapterCache
        ) {
          private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

          private val nullableStringAdapter: ResponseAdapter<String?> =
              NullableResponseAdapter(StringResponseAdapter)

          private val nullableListOfNullableFriendsAdapter:
              ResponseAdapter<List<TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends?>?>
              =
              NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friends(responseAdapterCache))))

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends {
            var __typename: String? = __typename
            var name: String? = null
            var homePlanet: String? = null
            var friends: List<TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends?>? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = stringAdapter.fromResponse(reader)
                1 -> name = stringAdapter.fromResponse(reader)
                2 -> homePlanet = nullableStringAdapter.fromResponse(reader)
                3 -> friends = nullableListOfNullableFriendsAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends(
              __typename = __typename!!,
              name = name!!,
              homePlanet = homePlanet,
              friends = friends
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends) {
            writer.beginObject()
            writer.name("__typename")
            stringAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            stringAdapter.toResponse(writer, value.name)
            writer.name("homePlanet")
            nullableStringAdapter.toResponse(writer, value.homePlanet)
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
                type = ResponseField.Type.Named.Other("String"),
                fieldName = "homePlanet",
              ),
              ResponseField(
                type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
                fieldName = "friends",
                fieldSets = listOf(
                  ResponseField.FieldSet("Droid", Friends.CharacterFriends.RESPONSE_FIELDS),
                  ResponseField.FieldSet("Human", Friends.CharacterFriends.RESPONSE_FIELDS),
                  ResponseField.FieldSet(null, Friends.OtherFriends.RESPONSE_FIELDS),
                ),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }

          class Friends(
            responseAdapterCache: ResponseAdapterCache
          ) :
              ResponseAdapter<TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends>
              {
            val CharacterFriendsAdapter: CharacterFriends =
                com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends.CharacterFriends(responseAdapterCache)

            val OtherFriendsAdapter: OtherFriends =
                com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends.OtherFriends(responseAdapterCache)

            override fun fromResponse(reader: JsonReader):
                TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends {
              reader.beginObject()
              check(reader.nextName() == "__typename")
              val typename = reader.nextString()

              return when(typename) {
                "Droid" -> CharacterFriendsAdapter.fromResponse(reader, typename)
                "Human" -> CharacterFriendsAdapter.fromResponse(reader, typename)
                else -> OtherFriendsAdapter.fromResponse(reader, typename)
              }
              .also { reader.endObject() }
            }

            override fun toResponse(writer: JsonWriter,
                value: TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends) {
              when(value) {
                is TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends.CharacterFriends -> CharacterFriendsAdapter.toResponse(writer, value)
                is TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends.OtherFriends -> OtherFriendsAdapter.toResponse(writer, value)
              }
            }

            class CharacterFriends(
              responseAdapterCache: ResponseAdapterCache
            ) {
              private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

              private val episodeAdapter: ResponseAdapter<Episode> = Episode_ResponseAdapter

              fun fromResponse(reader: JsonReader, __typename: String?):
                  TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends.CharacterFriends {
                var __typename: String? = __typename
                var firstAppearsIn: Episode? = null
                while(true) {
                  when (reader.selectName(RESPONSE_NAMES)) {
                    0 -> __typename = stringAdapter.fromResponse(reader)
                    1 -> firstAppearsIn = episodeAdapter.fromResponse(reader)
                    else -> break
                  }
                }
                return TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends.CharacterFriends(
                  __typename = __typename!!,
                  firstAppearsIn = firstAppearsIn!!
                )
              }

              fun toResponse(writer: JsonWriter,
                  value: TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends.CharacterFriends) {
                writer.beginObject()
                writer.name("__typename")
                stringAdapter.toResponse(writer, value.__typename)
                writer.name("firstAppearsIn")
                episodeAdapter.toResponse(writer, value.firstAppearsIn)
                writer.endObject()
              }

              companion object {
                val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                  ResponseField.Typename,
                  ResponseField(
                    type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Episode")),
                    fieldName = "firstAppearsIn",
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
                  TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends.OtherFriends {
                var __typename: String? = __typename
                while(true) {
                  when (reader.selectName(RESPONSE_NAMES)) {
                    0 -> __typename = stringAdapter.fromResponse(reader)
                    else -> break
                  }
                }
                return TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends.OtherFriends(
                  __typename = __typename!!
                )
              }

              fun toResponse(writer: JsonWriter,
                  value: TestQuery.Data.Search.CharacterSearch.Friends.CharacterHumanFriends.Friends.OtherFriends) {
                writer.beginObject()
                writer.name("__typename")
                stringAdapter.toResponse(writer, value.__typename)
                writer.endObject()
              }

              companion object {
                val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                  ResponseField.Typename
                )

                val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
              }
            }
          }
        }

        class OtherFriends(
          responseAdapterCache: ResponseAdapterCache
        ) {
          private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Search.CharacterSearch.Friends.OtherFriends {
            var __typename: String? = __typename
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = stringAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Search.CharacterSearch.Friends.OtherFriends(
              __typename = __typename!!
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Search.CharacterSearch.Friends.OtherFriends) {
            writer.beginObject()
            writer.name("__typename")
            stringAdapter.toResponse(writer, value.__typename)
            writer.endObject()
          }

          companion object {
            val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField.Typename
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }
        }
      }
    }

    class StarshipSearch(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Search.StarshipSearch {
        var __typename: String? = __typename
        var name: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Search.StarshipSearch(
          __typename = __typename!!,
          name = name!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Search.StarshipSearch) {
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

    class OtherSearch(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Search.OtherSearch {
        var __typename: String? = __typename
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Search.OtherSearch(
          __typename = __typename!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Search.OtherSearch) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.endObject()
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.Typename
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }
  }
}
