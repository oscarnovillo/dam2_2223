package org.example

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    println("Hello, world!")

    val apolloClient = ApolloClient.builder()
        .serverUrl("https://rickandmortyapi.com/graphql")
        .build()
    runBlocking {
       val job = launch { apolloClient.query(FirstQuery()).await().data?.let {
            println(it.characters?.results?.joinToString {result -> result?.name.toString()  })
        }
          

       }
    }






}

