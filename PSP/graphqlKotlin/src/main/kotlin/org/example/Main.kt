package org.example

import com.apollographql.apollo3.ApolloClient

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.rickMorty.FirstQuery

fun main(args: Array<String>) {
    println("Hello, world!")

    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://rickandmortyapi.com/graphql")
        .build()

    runBlocking {
        apolloClient.query(FirstQuery()).execute().data?.let {
            println(it.characters?.results?.map { it?.name })
        }
    }


}

