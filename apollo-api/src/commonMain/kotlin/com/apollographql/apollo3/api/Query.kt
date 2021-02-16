package com.apollographql.apollo3.api

import okio.ByteString

/**
 * Represents a GraphQL query that will be sent to the server.
 */
interface Query<D : Operation.Data> : Operation<D>
