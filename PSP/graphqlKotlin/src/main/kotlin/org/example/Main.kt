package org.example

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException

fun main(args: Array<String>) {
        println("Hello, world!")

        val apolloClient = ApolloClient.builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()

    apolloClient.query(FirstQuery()).enqueue(object : ApolloCall.Callback<FirstQuery.Data>() {
        override fun onFailure(e: ApolloException) {
            println("Failure $e")
        }

        override fun onResponse(response: Response<FirstQuery.Data>) {
            println("Success")
            response.data?.characters?.results?.forEach { println(it?.name) }
        }
    })


    }

