// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_in_fragment.fragment

import com.apollographql.apollo3.api.Fragment
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.ResponseField
import com.apollographql.apollo3.api.internal.ResponseAdapter
import com.example.fragment_in_fragment.fragment.adapter.PlanetFragmentImpl_ResponseAdapter
import kotlin.String
import kotlin.collections.List

class PlanetFragmentImpl : Fragment<PlanetFragmentImpl.Data> {
  override fun adapter(customScalarAdapters: ResponseAdapterCache): ResponseAdapter<Data> {
    val adapter = customScalarAdapters.getFragmentAdapter("PlanetFragmentImpl") {
      PlanetFragmentImpl_ResponseAdapter(customScalarAdapters)
    }
    return adapter
  }

  override fun responseFields(): List<ResponseField.FieldSet> = listOf(
    ResponseField.FieldSet(null, PlanetFragmentImpl_ResponseAdapter.RESPONSE_FIELDS)
  )
  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  /**
   * A large mass, planet or planetoid in the Star Wars Universe, at the time of
   * 0 ABY.
   */
  data class Data(
    override val __typename: String = "Planet",
    /**
     * The name of this planet.
     */
    override val name: String?
  ) : PlanetFragment, Fragment.Data
}
