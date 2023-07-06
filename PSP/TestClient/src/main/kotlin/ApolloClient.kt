package org.example

import com.apollographql.apollo3.ApolloClient

object ApolloClient {

    init {

    }

    fun geClient() : ApolloClient
    {
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")

            .build()
    }

}
