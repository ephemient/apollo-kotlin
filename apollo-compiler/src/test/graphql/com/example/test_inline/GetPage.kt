// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.test_inline

import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.ResponseField
import com.apollographql.apollo3.api.internal.QueryDocumentMinifier
import com.apollographql.apollo3.api.internal.ResponseAdapter
import com.example.test_inline.adapter.GetPage_ResponseAdapter
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class GetPage : Query<GetPage.Data> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): String = OPERATION_NAME

  override fun adapter(customScalarAdapters: ResponseAdapterCache): ResponseAdapter<Data> {
    val adapter = customScalarAdapters.getOperationAdapter(name()) {
      GetPage_ResponseAdapter(customScalarAdapters)
    }
    return adapter
  }

  override fun responseFields(): List<ResponseField.FieldSet> = listOf(
    ResponseField.FieldSet(null, GetPage_ResponseAdapter.RESPONSE_FIELDS)
  )
  data class Data(
    val collection: Collection
  ) : Operation.Data {
    interface Collection {
      val __typename: String

      val items: List<Items>

      interface Items {
        val title: String
      }

      data class ParticularCollectionCollection(
        override val __typename: String,
        override val items: List<Items>
      ) : Collection {
        interface Items : Collection.Items {
          val __typename: String

          data class ParticularItemItems(
            override val title: String,
            override val __typename: String,
            val image: String
          ) : Items

          data class OtherItems(
            override val title: String,
            override val __typename: String
          ) : Collection.Items, Items

          companion object {
            fun Items.asParticularItemItems(): ParticularItemItems? = this as? ParticularItemItems
          }
        }
      }

      data class OtherCollection(
        override val __typename: String,
        override val items: List<Items>
      ) : Collection {
        data class Items(
          override val title: String
        ) : Collection.Items
      }

      companion object {
        fun Collection.asParticularCollectionCollection(): ParticularCollectionCollection? = this
            as? ParticularCollectionCollection
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "09dd0a176a2233eccc3b2d3a76f25a1083460354f399f8b1aaf172c18cfc202b"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query GetPage {
          |  collection {
          |    __typename
          |    items {
          |      title
          |    }
          |    ... on ParticularCollection {
          |      items {
          |        __typename
          |        ... on ParticularItem {
          |          image
          |        }
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "GetPage"
  }
}
