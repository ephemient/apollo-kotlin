// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.root_query_inline_fragment

import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.ResponseField
import com.apollographql.apollo3.api.internal.QueryDocumentMinifier
import com.apollographql.apollo3.api.internal.ResponseAdapter
import com.example.root_query_inline_fragment.adapter.TestQuery_ResponseAdapter
import com.example.root_query_inline_fragment.type.Episode
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery : Query<TestQuery.Data> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): String = OPERATION_NAME

  override fun adapter(customScalarAdapters: ResponseAdapterCache): ResponseAdapter<Data> {
    val adapter = customScalarAdapters.getOperationAdapter(name()) {
      TestQuery_ResponseAdapter(customScalarAdapters)
    }
    return adapter
  }

  override fun responseFields(): List<ResponseField.FieldSet> = listOf(
    ResponseField.FieldSet("Query", TestQuery_ResponseAdapter.QueryData.RESPONSE_FIELDS),
    ResponseField.FieldSet(null, TestQuery_ResponseAdapter.OtherData.RESPONSE_FIELDS),
  )
  /**
   * The query type, represents all of the entry points into our object graph
   */
  interface Data : Operation.Data {
    val __typename: String

    data class QueryData(
      override val __typename: String,
      val hero: Hero?,
      val droid: Droid?
    ) : Data {
      /**
       * A character from the Star Wars universe
       */
      interface Hero {
        val __typename: String

        /**
         * The name of the character
         */
        val name: String

        /**
         * The movies this character appears in
         */
        val appearsIn: List<Episode?>

        data class HumanHero(
          override val __typename: String,
          /**
           * The name of the character
           */
          override val name: String,
          /**
           * The movies this character appears in
           */
          override val appearsIn: List<Episode?>,
          /**
           * Height in the preferred unit, default is meters
           */
          val height: Double?
        ) : Hero

        data class OtherHero(
          override val __typename: String,
          /**
           * The name of the character
           */
          override val name: String,
          /**
           * The movies this character appears in
           */
          override val appearsIn: List<Episode?>
        ) : Hero

        companion object {
          fun Hero.asHumanHero(): HumanHero? = this as? HumanHero
        }
      }

      /**
       * An autonomous mechanical character in the Star Wars universe
       */
      interface Droid {
        val __typename: String

        data class DroidDroid(
          override val __typename: String,
          /**
           * What others call this droid
           */
          val name: String,
          /**
           * This droid's primary function
           */
          val primaryFunction: String?
        ) : Droid

        data class OtherDroid(
          override val __typename: String
        ) : Droid

        companion object {
          fun Droid.asDroidDroid(): DroidDroid? = this as? DroidDroid
        }
      }
    }

    data class OtherData(
      override val __typename: String
    ) : Data

    companion object {
      fun Data.asQueryData(): QueryData? = this as? QueryData
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "25584d760eab0f41189b9f2bbdbba3c0ec491aced65ef23924ecdc8f41ffe78c"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  __typename
          |  ... on Query {
          |    __typename
          |    hero {
          |      __typename
          |      name
          |      appearsIn
          |      ... on Human {
          |        height
          |      }
          |    }
          |    droid(id: 1) {
          |      __typename
          |      ... on Droid {
          |        name
          |        primaryFunction
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "TestQuery"
  }
}
