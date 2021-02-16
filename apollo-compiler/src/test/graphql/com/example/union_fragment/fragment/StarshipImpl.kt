// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.union_fragment.fragment

import com.apollographql.apollo3.api.Fragment
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.ResponseField
import com.apollographql.apollo3.api.internal.ResponseAdapter
import com.example.union_fragment.fragment.adapter.StarshipImpl_ResponseAdapter
import kotlin.String
import kotlin.collections.List

class StarshipImpl : Fragment<StarshipImpl.Data> {
  override fun adapter(customScalarAdapters: ResponseAdapterCache): ResponseAdapter<Data> {
    val adapter = customScalarAdapters.getFragmentAdapter("StarshipImpl") {
      StarshipImpl_ResponseAdapter(customScalarAdapters)
    }
    return adapter
  }

  override fun responseFields(): List<ResponseField.FieldSet> = listOf(
    ResponseField.FieldSet(null, StarshipImpl_ResponseAdapter.RESPONSE_FIELDS)
  )
  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  data class Data(
    override val __typename: String = "Starship",
    /**
     * The name of the starship
     */
    override val name: String
  ) : Starship, Fragment.Data
}
