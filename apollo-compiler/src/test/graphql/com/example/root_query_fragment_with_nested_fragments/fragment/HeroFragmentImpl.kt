// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.root_query_fragment_with_nested_fragments.fragment

import com.apollographql.apollo3.api.Fragment
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.ResponseField
import com.apollographql.apollo3.api.internal.ResponseAdapter
import com.example.root_query_fragment_with_nested_fragments.fragment.adapter.HeroFragmentImpl_ResponseAdapter
import kotlin.String
import kotlin.collections.List

class HeroFragmentImpl : Fragment<HeroFragmentImpl.Data> {
  override fun adapter(customScalarAdapters: ResponseAdapterCache): ResponseAdapter<Data> {
    val adapter = customScalarAdapters.getFragmentAdapter("HeroFragmentImpl") {
      HeroFragmentImpl_ResponseAdapter(customScalarAdapters)
    }
    return adapter
  }

  override fun responseFields(): List<ResponseField.FieldSet> = listOf(
    ResponseField.FieldSet(null, HeroFragmentImpl_ResponseAdapter.RESPONSE_FIELDS)
  )
  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  /**
   * A character from the Star Wars universe
   */
  data class Data(
    override val __typename: String = "Character",
    /**
     * The name of the character
     */
    override val name: String
  ) : HeroFragment, Fragment.Data
}
